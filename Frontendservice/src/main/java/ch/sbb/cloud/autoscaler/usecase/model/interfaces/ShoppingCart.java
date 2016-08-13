package ch.sbb.cloud.autoscaler.usecase.model.interfaces;

import ch.sbb.cloud.autoscaler.usecase.model.interfaces.base.BaseIdModelClass;
import org.springframework.hateoas.ResourceSupport;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by thomas on 29.07.16.
 */
public class ShoppingCart {

    private Customer customer;

    private Date timestamp = new Date();

    private Set<ShoppingCartItem> items = new HashSet<>();

    public Set<ShoppingCartItem> getItems() {
        return items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
