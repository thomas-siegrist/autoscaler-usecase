cd ./Backendservice
mvn clean install docker:build
docker push registry.openshift.local/usecase/backendservice
cd ../Frontendservice
mvn clean install docker:build
docker push registry.openshift.local/usecase/frontendservice
cd ../Paymentservice
mvn clean install docker:build
docker push registry.openshift.local/usecase/paymentservice
cd ../Printservice
mvn clean install docker:build
docker push registry.openshift.local/usecase/printservice
cd ../Emailservice
mvn clean install docker:build
docker push registry.openshift.local/usecase/emailservice
