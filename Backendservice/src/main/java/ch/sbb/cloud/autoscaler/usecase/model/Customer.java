/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2016.
 */

package ch.sbb.cloud.autoscaler.usecase.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import ch.sbb.cloud.autoscaler.usecase.model.base.BaseIdEntity;

@Entity
public class Customer extends BaseIdEntity {

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

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

}
