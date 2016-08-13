/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2016.
 */

package ch.sbb.cloud.autoscaler.usecase.model.interfaces;

import ch.sbb.cloud.autoscaler.usecase.model.interfaces.base.BaseIdModelClass;
import org.springframework.hateoas.ResourceSupport;

import java.util.Set;

public class Customer {

    private String firstName;

    private String lastName;

    private String email;

    private Set<ShoppingCart> shoppingCarts;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(Set<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

}
