package com.test.employee.repo;

import com.test.common.repo.AbstractRepo;
import com.test.employee.model.Employee;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepo extends AbstractRepo<Employee> {
    public EmployeeRepo(SessionFactory sessionFactory){
        super(sessionFactory);
    }
}
