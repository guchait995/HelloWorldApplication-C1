package com.test.apiResponse;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public class EmployeeResponse {
    public long id;
    public String name;
    public String department;
    public Date created;
    public Date modified;

    public EmployeeResponse() {
    }

    public String getCreated() {
        return created.toLocalDate().toString();
    }

    public String getModified() {
        return modified.toLocalDate().toString();
    }
}
