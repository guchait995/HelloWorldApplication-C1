package com.test.employee.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;

public class EmployeeRequest {
    @JsonProperty()
    private String name;

    @JsonProperty()
    private String department;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
