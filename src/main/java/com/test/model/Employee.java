package com.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.common.model.Entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "employee")
@javax.persistence.SequenceGenerator(name = "default_gen", sequenceName = "employee_id_seq", allocationSize = 1)
public class Employee extends Entity {
    @JsonProperty()
    @Column(name = "name",nullable = false)
    private String name;

    @JsonProperty()
    @Column(name = "department")
    private String department;

    public Employee() {
    }

    public Employee(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
