package ch.sbb.cloud.autoscaler.usecase.model.interfaces;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by thomas on 04.08.16.
 */
public class PrintTask extends ResourceSupport {

    private Integer numberOfOrders;

    public Integer getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(Integer numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }
}
