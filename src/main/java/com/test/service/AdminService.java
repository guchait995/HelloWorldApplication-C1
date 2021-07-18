package com.test.service;

import com.test.apiResponse.UserResponse;
import com.test.repo.UserRepo;

import java.util.List;
import java.util.stream.Collectors;

public class AdminService {
    UserRepo userRepo;
    public AdminService(UserRepo userRepo){
        this.userRepo=userRepo;
    }

    public String getZone(){
       List<UserResponse> users= userRepo.getAllUsers().stream().filter(user->user.isPositive).collect(Collectors.toList());
       int noOFUser=users.size();
       if(noOFUser==0){
           return "green";
       }else if(noOFUser<5){
           return "orange";
       }else{
           return "red";
       }
    }
}
