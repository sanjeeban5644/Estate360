package com.sanjeeban.CoreApartmentService.dataAccessLayer;


import com.sanjeeban.CoreApartmentService.customException.*;
import com.sanjeeban.CoreApartmentService.dto.GetAllResidentsResponse;
import com.sanjeeban.CoreApartmentService.dto.GetAllUsersResponse;
import com.sanjeeban.CoreApartmentService.dto.SaveNewResidentRequest;
import com.sanjeeban.CoreApartmentService.dto.SaveNewUserRequest;
import com.sanjeeban.CoreApartmentService.entity.*;
import com.sanjeeban.CoreApartmentService.jdbc.JdbcUtil;
import com.sanjeeban.CoreApartmentService.repository.*;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserResidentDatabaseService {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    ApartmentRepository apartmentRepository;

    @Autowired
    ResidentProfileRepository residentProfileRepository;


    private final PasswordEncoder passwordEncoder;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    JdbcUtil jdbcUtil;

    @Autowired
    RoleMasterRepository roleMasterRepository;

    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    public UserResidentDatabaseService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public boolean saveUser(Long userId, SaveNewUserRequest request) {

        UserAccount newUser = new UserAccount();
        newUser.setUserId(userId);
        newUser.setName(request.getName());
        newUser.setEmail(request.getEmail());
        newUser.setMobile(request.getMobile());
        newUser.setUserName(request.getUserName());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setStatus("ACTIVE");

        userAccountRepository.saveAndFlush(newUser);

        // save the role of the user in role table
        String role = request.getRole();
        Long roleId = null;
        try{
            roleId = roleMasterRepository.getRoleByRoleName(role).getRoleId();
        }catch(Exception e){
            e.printStackTrace();
        }


        if(roleId==null){
            roleId = 0000L;
        }

        UserRole newUserRoleMapping = new UserRole();
        newUserRoleMapping.setUserId(userId);
        newUserRoleMapping.setRoleId(roleId);

        userRoleRepository.save(newUserRoleMapping);


        return userAccountRepository.existsByUserId(userId);
    }

    public UserAccount getUser(Long userId) {
        return userAccountRepository.getUserWithUserId(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User Not Found with User Id: " + userId)
                );
    }

    public Optional<UserAccount> checkAndReturnUser(Long userId) {
        return userAccountRepository.getUserWithUserId(userId);
    }

    public boolean checkUser(Long userId) {
        return userAccountRepository.existsByUserId(userId);
    }

    public Page<GetAllUsersResponse> getAllUsers(int page,int size) {
        List<GetAllUsersResponse> listOfUsers = new ArrayList<>();
        Pageable pageable = PageRequest.of(page,size);
        Page<UserAccount> userList = userAccountRepository.findAll(pageable);
        return userList.map(user ->
                modelMapper.map(user, GetAllUsersResponse.class)
        );
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
                .map(UserAccount::getUserName)
                .orElseThrow(() -> new NameDoesNotExistsException("Name does not exist for User with userId : "+userId));
    }

    public UserAccount getUserName(String username) {
        return userAccountRepository.getUserByUserName(username)
                .orElseThrow(() -> new UserNotFoundException("User does not exist with UserName : "+username));
    }

    public String getRoleFromUserId(Long userId) {

        String role = "";
        String sqlQuery = "select tr.role_name from \n" +
                "estatedb.t_role_master tr, \n" +
                "estatedb.t_user_role tur where \n" +
                "tr.role_id = tur.role_id\n" +
                "and tur.user_id=?";

        try(Connection conn = jdbcUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuery)){

            ps.setLong(1,userId);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    role = rs.getString(1);
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Exception in db connection -1");
        }
        return role;
    }

//    public UserDetails getUserByUserName(String username) {
//        UserDetails obj =  userAccountRepository.getUserByUserName(username)
//                .orElseThrow(() -> new UserNotFoundException("User Not found with UserName : "+username));
//        String user = obj.getUsername();
//        String pass = obj.getPassword();
//        return obj;
//    }
}
