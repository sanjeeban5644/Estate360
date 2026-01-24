package com.sanjeeban.CoreApartmentService.dataAccessLayer;


import com.sanjeeban.CoreApartmentService.customException.*;
import com.sanjeeban.CoreApartmentService.dto.GetAllResidentsResponse;
import com.sanjeeban.CoreApartmentService.dto.GetAllUsersResponse;
import com.sanjeeban.CoreApartmentService.dto.SaveNewResidentRequest;
import com.sanjeeban.CoreApartmentService.dto.SaveNewUserRequest;
import com.sanjeeban.CoreApartmentService.entity.Apartment;
import com.sanjeeban.CoreApartmentService.entity.ResidentProfile;
import com.sanjeeban.CoreApartmentService.entity.UserAccount;
import com.sanjeeban.CoreApartmentService.repository.ApartmentRepository;
import com.sanjeeban.CoreApartmentService.repository.ResidentProfileRepository;
import com.sanjeeban.CoreApartmentService.repository.UserAccountRepository;
import jakarta.persistence.AttributeOverride;
import jakarta.transaction.Transactional;
import org.apache.commons.collections4.Get;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserResidentDatabaseService {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    ApartmentRepository apartmentRepository;

    @Autowired
    ResidentProfileRepository residentProfileRepository;

    @Autowired
    ModelMapper modelMapper;

    public boolean saveUser(Long userId, SaveNewUserRequest request) {

        UserAccount newUser = new UserAccount();
        newUser.setUserId(userId);
        newUser.setName(request.getName());
        newUser.setEmail(request.getEmail());
        newUser.setMobile(request.getMobile());
        newUser.setUserName(request.getUserName());
        newUser.setPassword(request.getPassword());
        newUser.setStatus("ACTIVE");

        userAccountRepository.saveAndFlush(newUser);

        return userAccountRepository.existsByUserId(userId);
    }

    public UserAccount getUser(Long userId) {
        return userAccountRepository.getUserWithUserId(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User Not Found with User Id: " + userId)
                );
    }

    public boolean checkUser(Long userId) {
        return userAccountRepository.existsByUserId(userId);
    }

    public List<GetAllUsersResponse> getAllUsers() {
        List<GetAllUsersResponse> listOfUsers = new ArrayList<>();
        List<UserAccount> userList = userAccountRepository.findAll();
        for(UserAccount user : userList){
            GetAllUsersResponse obj = new GetAllUsersResponse();
            obj = modelMapper.map(user,GetAllUsersResponse.class);
            listOfUsers.add(obj);
        }
        return listOfUsers;
    }

    public boolean checkApartmentAvailability(Long apartmentId) {
        Apartment apartment = apartmentRepository.findByApartmentId(apartmentId).orElseThrow(() ->
                new ApartmentNotFoundException("Apartment not found with Apartment Id  : "+String.valueOf(apartmentId)));

        return apartment.getAvailability();
    }

    public boolean checkResident(Long residentId) {
        return residentProfileRepository.existsByresidentId(residentId);
    }

    public void saveResident(Long residentId, SaveNewResidentRequest request) {

        ResidentProfile resident = new ResidentProfile();
        resident.setResidentId(residentId);
        resident.setUserId(Long.parseLong(request.getUserId()));
        resident.setApartmentId(Long.parseLong(request.getApartmentId()));
        resident.setResidentGrpId(Long.parseLong(request.getResidentGroupId()));
        resident.setResidentType(request.getResidentType());

        resident.setDob(parseDob(request.getDob()));
        resident.setAadhar(request.getAadhar());
        resident.setPreviousAddress(request.getPrevAddress());
        resident.setCurrentAddress(request.getCurrentAddress());

        residentProfileRepository.save(resident);
    }

    private LocalDate parseDob(String dob) {
        try {
            return LocalDate.parse(dob, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException ex) {
            throw new IllegalDateFormatException("Date entered is not in correct format.");
        }
    }


    public List<GetAllResidentsResponse> getAllResidents() {
        List<GetAllResidentsResponse> responseList = new ArrayList<>();

        List<ResidentProfile> listOfResidents = residentProfileRepository.findAll();

        for(ResidentProfile obj : listOfResidents){
            GetAllResidentsResponse resObj = new GetAllResidentsResponse();
            responseList.add(modelMapper.map(obj,GetAllResidentsResponse.class));
        }
        return responseList;
    }

    public String getEmailFromUserId(String userId) {
        Long id;
        try {
            id = Long.parseLong(userId);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid userId format: " + userId);
        }
        return userAccountRepository.getUserWithUserId(id)
                .map(UserAccount::getEmail)
                .orElseThrow(() -> new EmailDoesNotExistsException("Email does not exist for User with userId : "+userId));
    }

    public String getNameFromUserId(String userId) {
        Long id;
        try {
            id = Long.parseLong(userId);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid userId format: " + userId);
        }
        return userAccountRepository.getUserWithUserId(id)
                .map(UserAccount::getUsername)
                .orElseThrow(() -> new NameDoesNotExistsException("Name does not exist for User with userId : "+userId));
    }

    public UserDetails getUserByUserName(String username) {
        UserDetails obj =  userAccountRepository.getUserByUserName(username)
                .orElseThrow(() -> new UserNotFoundException("User Not found with UserName : "+username));
        String user = obj.getUsername();
        String pass = obj.getPassword();
        return obj;
    }
}
