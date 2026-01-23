package com.sanjeeban.CoreApartmentService.controller;

import com.sanjeeban.CoreApartmentService.dto.*;
import com.sanjeeban.CoreApartmentService.Iservice.IUserResidentService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/userResident")
public class UserResidentController {

    @Autowired
    IUserResidentService iUserResidentService;

    @Operation(summary = "This validates and saves a new User", description = "This validates and saves a new User")
    @PostMapping(path="/saveNewUser")
    public ResponseEntity<SaveUserResidentResponse> saveNewUser(@RequestBody SaveNewUserRequest request){
        SaveUserResidentResponse response = new SaveUserResidentResponse();
        response = iUserResidentService.saveNewUserService(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @Operation(summary = "This gets all the users",description = "This gets all the users")
    @GetMapping(path="/getAllUsers")
    public ResponseEntity<List<GetAllUsersResponse>> getAllUsers(){
        List<GetAllUsersResponse> response = new ArrayList<>();
        response = iUserResidentService.getAllUsers();
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }


    @Operation(summary = "This validates and saves a new Resident", description = "This validates and saves a new Resident")
    @PostMapping(path="/saveNewResident")
    public ResponseEntity<SaveUserResidentResponse> saveNewResident(@RequestBody SaveNewResidentRequest request){
        SaveUserResidentResponse response = new SaveUserResidentResponse();
        response = iUserResidentService.saveNewResident(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @Operation(summary = "This gets all the residents",description = "This gets all the residents")
    @GetMapping(path="/getAllResidents")
    public ResponseEntity<List<GetAllResidentsResponse>> getAllResidents(){
        List<GetAllResidentsResponse> response = new ArrayList<>();
        response = iUserResidentService.getAllResidents();
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }


}
