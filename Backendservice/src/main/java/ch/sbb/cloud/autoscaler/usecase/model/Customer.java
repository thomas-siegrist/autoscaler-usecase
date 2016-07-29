/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2016.
 */

package ch.sbb.cloud.autoscaler.usecase.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import ch.sbb.cloud.autoscaler.usecase.model.base.BaseIdEntity;

@Entity
public class Customer extends BaseIdEntity {

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    @OneToMany(orphanRemoval = true, mappedBy = "customer", fetch = FetchType.EAGER)
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
