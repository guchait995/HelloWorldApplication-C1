package com.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.common.model.Entity;

import javax.persistence.Column;
import javax.persistence.Table;


@javax.persistence.Entity
@Table(name = "appuser")
@javax.persistence.SequenceGenerator(name = "default_gen", sequenceName = "appuser_id_seq", allocationSize = 1)
public class AppUser extends Entity {
    @JsonProperty()
    @Column(name = "name",nullable = false)
    public String name;

    @JsonProperty()
    @Column(name = "phoneNumber",nullable = false)
    public String phoneNumber;

    @JsonProperty()
    @Column(name = "pinCode",nullable = false)
    public String pinCode;

    @JsonProperty()
    @Column(name = "isAdmin",nullable = false)
    public boolean isAdmin = false;

    @JsonProperty()
    @Column(name = "isPositive")
    public boolean isPositive = false;

    public AppUser() {
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPinCode() {
        return pinCode;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isPositive() {
        return isPositive;
    }
}
