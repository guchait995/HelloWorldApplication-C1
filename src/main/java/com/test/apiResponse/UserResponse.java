package com.test.apiResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;

public class UserResponse {
    @JsonProperty()
    public String name;

    @JsonProperty()
    public String phoneNumber;

    @JsonProperty()
    public String pinCode;

    @JsonProperty()
    public boolean isAdmin = false;

    @JsonProperty()
    public boolean isPositive = false;


}
