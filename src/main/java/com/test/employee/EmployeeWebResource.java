package com.test.employee;


import com.codahale.metrics.annotation.Timed;
import com.test.employee.model.Employee;
import com.test.employee.model.EmployeeRequest;
import com.test.employee.repo.EmployeeRepo;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("employee")
@Produces(APPLICATION_JSON)
public class EmployeeWebResource {

    private EmployeeRepo employeeRepo;
    public EmployeeWebResource(EmployeeRepo employeeRepo){
        this.employeeRepo=employeeRepo;
    }

    @GET
    @UnitOfWork
    @Path("/{employeeId}")
    public Response getEmployees(@PathParam("employeeId") Long employeeId){
        Employee employee= null;
        try {
            employee = employeeRepo.findById(employeeId).orElseThrow(RuntimeException::new);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return Response.ok(employee).build();
    }

    @POST
    @Path("/updateOrCreate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public Response saveEmployee(EmployeeRequest employeeRequest){
        Employee employee;
        try{
            employee=new Employee(employeeRequest.getName(), employeeRequest.getDepartment());
            employeeRepo.save(employee);
        }catch(Exception e){
            throw new RuntimeException("Unable to update or create new employee !!");
        }
        return Response.ok(employee).build();
    }

}
