package com.sanjeeban.CoreApartmentService.service;

import com.sanjeeban.CoreApartmentService.dataAccessLayer.UserResidentDatabaseService;
import com.sanjeeban.CoreApartmentService.dto.*;
import com.sanjeeban.CoreApartmentService.Iservice.IUserResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class UserResidentService implements IUserResidentService {



    @Autowired
    UserResidentDatabaseService userResidentDatabaseService;


    @Override
    public SaveUserResidentResponse saveNewUserService(SaveNewUserRequest request){
        SaveUserResidentResponse response = new SaveUserResidentResponse();
        
        String name = request.getName()==null?"":request.getName();
        String email = request.getEmail()==null?"":request.getEmail();
        String mobile = request.getMobile()==null?"":request.getMobile();
        String status  = "ACTIVE";
        
        String msg = validateRequest(name,email,mobile,status);
        if(!msg.equals("200")){
            response.setResponseCode("400");
            response.setResponseMsg(msg);
            response.setRemarks("Validation Error");
            return response;
        }

        // request is validated.

        // generating unique userId.
        Long userId = generateUserId();
        while(isValidUserId(userId)){
            userId = generateUserId();
        }

        boolean isSaved = userResidentDatabaseService.saveUser(userId,request);

        if(isSaved){
            response.setUserId(String.valueOf(userId));
            response.setEmailConfirmation("");
            response.setResponseCode("201");
            response.setResponseMsg("Saved Details successfully");
            response.setRemarks("Saved");
        }else{
            response.setUserId(String.valueOf(userId));
            response.setEmailConfirmation("");
            response.setResponseCode("400");
            response.setResponseMsg("Could not save into database");
            response.setRemarks("ERROR");
        }

        return response;
    }



    @Override
    public List<GetAllUsersResponse> getAllUsers() {
        return userResidentDatabaseService.getAllUsers();
    }

    @Override
    public SaveUserResidentResponse saveNewResident(SaveNewResidentRequest request) {

        SaveUserResidentResponse response = new SaveUserResidentResponse();

        String msg = validateRequest(request);
        if(!msg.equals("200")){
            response.setResponseCode("400");
            response.setResponseMsg(msg);
            response.setRemarks("Validation Error");
            return response;
        }
        Long residentId = generateResidentId();

        while(isValidResidentId(residentId)){
            residentId = generateUserId();
        }

        userResidentDatabaseService.saveResident(residentId,request);

        response.setUserId(String.valueOf(request.getUserId()));
        response.setRemarks(String.valueOf(residentId));
        response.setEmailConfirmation("");
        response.setResponseCode("201");
        response.setResponseMsg("Saved Details successfully");
        response.setRemarks("Saved");

        return response;
    }

    @Override
    public List<GetAllResidentsResponse> getAllResidents() {
        return userResidentDatabaseService.getAllResidents();
    }

    private boolean isValidResidentId(Long residentId) {
        return userResidentDatabaseService.checkResident(residentId);
    }

    private String validateRequest(SaveNewResidentRequest request) {
        String retMsg = "200";

        String userId = request.getUserId();
        String apartmentId = request.getApartmentId();
        String residentType = request.getResidentType();
        String aadhar = request.getAadhar();

        if(userId.isEmpty() || apartmentId.isEmpty() || residentType.isEmpty() || aadhar.isEmpty()){
            retMsg = "UserId, ApartmentId, residentType and Aadhar are mandatory fields.";
            return retMsg;
        }
        if(residentType.equals("TENANT") && request.getCurrentAddress().isEmpty()){
            retMsg = "Current Address is mandatory for Tenants";
            return retMsg;
        }

        // validate userId;

        boolean isValidUserId = isValidUserId(Long.valueOf(userId));
        if(!isValidUserId){
            retMsg = "UserId does not exist.";
            return retMsg;
        }

        // check apartment availability
        boolean isApartmentAvailable = isApartmentAvailable(Long.valueOf(apartmentId));
        if(!isApartmentAvailable){
            retMsg = "Apartment is not available.";
            return retMsg;
        }


        return retMsg;
    }


    private boolean isApartmentAvailable(Long apartmentId){
        return userResidentDatabaseService.checkApartmentAvailability(apartmentId);
    }

    private boolean isValidUserId(Long userId) {
        return userResidentDatabaseService.checkUser(userId);
    }

    private Long generateUserId() {
        Random random = new Random();
        // Generate a number between 100000 and 999999
        return 100000 + random.nextLong(900000);
    }

    private Long generateResidentId() {
        Random random = new Random();
        return 10000000 + random.nextLong(90000000);
    }

    private String validateRequest(String name, String email, String mobile, String status) {
        String retMsg = "200";
        if(name.isEmpty() || email.isEmpty() || mobile.isEmpty()){
            retMsg = "Name, Email and Mobile are mandatory fields.";
            return retMsg;
        }
        if(!checkEmail(email)){
            return "Email is not in correct format. Please validate";
        }
        if(!checkMobile(mobile)){
            return "Mobile number is not in correct format. Please validate";
        }
        return retMsg;
    }

    private boolean checkEmail(String email) {
        // Regex for a basic email validation
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean checkMobile(String mobile) {
        // Regex: must be 10 digits, starting with 6-9
        String mobileRegex = "^[6-9]\\d{9}$";
        Pattern pattern = Pattern.compile(mobileRegex);
        Matcher matcher = pattern.matcher(mobile);
        return matcher.matches();
    }





}
