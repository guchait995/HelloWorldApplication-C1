package com.test.webResource;


import com.codahale.metrics.annotation.Timed;
import com.test.model.Employee;
import com.test.model.EmployeeRequest;
import com.test.repo.EmployeeRepo;
import com.test.apiResponse.EmployeeResponse;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("employee")
@Produces(APPLICATION_JSON)
public class EmployeeWebResource {
    private EmployeeRepo employeeRepo;
    private static final Logger LOGGER = LoggerFactory.getLogger(Employee.class);
    public EmployeeWebResource(EmployeeRepo employeeRepo){
        this.employeeRepo=employeeRepo;
    }
    @GET
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response getEmployees(){
        try{
            List<EmployeeResponse> employees=employeeRepo.getAllEmployees();
            return Response.ok(employees).build();
        }catch(Error e){
            return Response.ok().build();
        }
    }
    @GET
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{employeeId}")
    public Response getEmployee(@PathParam("employeeId") Long employeeId){
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
    public Response saveEmployee(Employee employee){
//        Employee employee;
        try{
//            employee=new Employee(employeeRequest.getName(), employeeRequest.getDepartment());
            employeeRepo.save(employee);
        }catch(Exception e){
            throw new RuntimeException("Unable to update or create new employee !!");
        }
        return Response.ok(employee).build();
    }

}
