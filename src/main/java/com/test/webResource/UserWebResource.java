package com.test.webResource;


import com.codahale.metrics.annotation.Timed;
import com.test.apiResponse.UserResponse;
import com.test.model.SelfAssessment;
import com.test.model.AppUser;
import com.test.repo.UserRepo;
import com.test.service.AdminService;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("user")
@Produces(APPLICATION_JSON)
public class UserWebResource {
    UserRepo userRepo;
    AdminService adminService;
    public UserWebResource(UserRepo userRepo, AdminService adminService) {
        this.userRepo=userRepo;
        this.adminService=adminService;
    }

    @GET
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response getUsers(){
        try{
            List<UserResponse> users=userRepo.getAllUsers();
            return Response.ok(users).build();
        }catch(Error e){
            return Response.ok().build();
        }
    }


    @POST
    @Path("/updateOrCreate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public Response saveUser(AppUser user){
        try{
            userRepo.save(user);
        }catch(Exception e){
            throw new RuntimeException("Unable to update or create new user !!");
        }
        return Response.ok(user).build();
    }


    @POST
    @Path("/selfAssement")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public Response saveSelfAssessment(SelfAssessment selfAssessment){
        try{
            userRepo.addSelfAssessment(selfAssessment);
        }catch(Exception e){
            throw new RuntimeException("Unable to update or create new user !!");
        }
        return Response.ok(selfAssessment).build();
    }

    @GET
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getZoneDetails")
    public Response getZoneDetails(){
        try{
            String zone=adminService.getZone();
            return Response.ok(zone).build();
        }catch(Error e){
            return Response.ok().build();
        }
    }
}
