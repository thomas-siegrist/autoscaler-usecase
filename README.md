# A simple UseCase to test Autoscalers

*This is a strongly simplified and headless Webshop for testing Autoscalers. It's built on the basics of a Microservice-Architecture and is being configured to be easily deployable on Docker-Based infrastructure (tested only on Openshift)*

## Deployment on Openshift
#### Prerequisites
* Docker installed
* Git installed
* OC cli installed
* Docker-Hub account

* Local Openshift installation
* An Openshift Project called "usecase"

> Use Openshift with the internal Docker-Registry listening on registry.openshift.local -> map this url to the IP of Openshift's internal registry in your /etc/hosts file if necessary)

#### Setup RabbitMQ
Login Docker Docker-Hub with your account:

    docker login hub.docker.com

Pull RabbitMQ tag it and push it to the local Openshift registry:

    docker pull rabbitmq:3-management
    docker tag rabbitmq:3-management registry.openshift.local/usecase/rabbitmq
    docker login registry.openshift.local
    docker push registry.openshift.local/usecase/rabbitmq

Login to Openshift, choose your project and setup a rabbitmq service:

    oc login
    oc project usecase
    oc new-app usecase/rabbitmq

Expose the RabbitMQ service (Port 15672) in order to view the RabbitMQ admin ui (login with guest/guest). In the RabbitMQ admin ui you can then create two **queues**:

* print-queue
* email-queue

#### Setup the Microservices
    git clone https://github.com/thomas-siegrist/autoscaler-usecase.git
    docker login registry.openshift.local

Build and deploy all services as Docker images to the Docker registry with the deploy-script in the root folder:

    ./deploy_all.sh

Create all the services in openshift:

    oc new-app usecase/Frontendservice
    oc new-app usecase/Backendservice
    oc new-app usecase/Paymentservice
    oc new-app usecase/Printservice
    oc new-app usecase/Emailservice

Expose the Frontendservice under frontendservice.openshift.local

Set the spring Stage of all the services via Environment-Variable to dev:

* Go to the Openshift console and click Browse / Deployments
* Click on the service (do this for each service separately)
* Click on Actions / Edit Yaml
* Scroll down to the entry called "Protocol TCP"

Insert the following code-snippet right after it:

    env:
		-
			name: STAGE
			value: dev 

  
#### Test the setup
Execute a REST POST and checkout one of the predefined shopping-carts. Possible values are 0, 1, 2 or 3:

    POST http://frontendservice.openshift.local/checkout/1  
