package com.sanjeeban.CoreApartmentService.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sanjeeban.CoreApartmentService.dataAccessLayer.UserResidentDatabaseService;
import com.sanjeeban.CoreApartmentService.dto.*;
import com.sanjeeban.CoreApartmentService.Iservice.IUserResidentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class UserResidentService implements IUserResidentService {

    @Value("${kakfa.topic.user-random_topic}")
    private String KAFKA_TOPIC_NAME;

    @Value("${kakfa.topic.email-topic}")
    private String EMAIL_KAFKA_TOPIC;


    private UserResidentDatabaseService userResidentDatabaseService;

    private final DiscoveryClient discoveryClient;

    private final RestClient restClient;

    private final KafkaTemplate<String,ObjectNode> kafkaTemplate;

    @Autowired
    public UserResidentService(UserResidentDatabaseService userResidentDatabaseService, DiscoveryClient discoveryClient, RestClient restClient, KafkaTemplate<String, ObjectNode> kafkaTemplate){
        this.userResidentDatabaseService = userResidentDatabaseService;
        this.discoveryClient = discoveryClient;
        this.restClient = restClient;
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public SaveUserResidentResponse saveNewUserService(SaveNewUserRequest request){
        SaveUserResidentResponse response = new SaveUserResidentResponse();
        
        String name = request.getName()==null?"":request.getName();
        String email = request.getEmail()==null?"":request.getEmail();
        String mobile = request.getMobile()==null?"":request.getMobile();
        String username = request.getUserName()==null?"":request.getUserName();
        String password = request.getPassword()==null?"":request.getPassword();
        String status  = "ACTIVE";
        
        String msg = validateRequest(name,email,mobile,status,username,password);
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



        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();

        json.put("sendTo","email");
        json.put("sendSubject","Welcome to Estate 360");
        json.put("body","this is the body");
        json.put("sendFrom","estate360@es.com");

        kafkaTemplate.send(EMAIL_KAFKA_TOPIC,"email-kafka",json);

        return response;
    }



    @Override
    public List<GetAllUsersResponse> getAllUsers(int page, int size) {
        return userResidentDatabaseService.getAllUsers(page,size).getContent();
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
        
        // sending mail to the receiver. 
        String email = getEmailUsingUserId(request.getUserId());
        String residentName = getMailUsingUserId(request.getUserId());


        String body = "Dear " + residentName + ",\n\n"
                + "We are delighted to welcome you to our complex! 🎉\n\n"
                + "Your Resident ID is " + String.valueOf(residentId) + ". This ID will serve as your official identification within the complex. "
                + "Please keep it safe and use it wisely, as it will be required for access and verification purposes.\n\n"
                + "We look forward to having you as part of our community.\n\n"
                + "Warm regards,\n"
                + "Complex Management Team";


        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();

        json.put("sendTo",email);
        json.put("sendSubject","Welcome to Estate 360");
        json.put("body",body);
        json.put("sendFrom","estate360@es.com");

        String emailResponse = "";

        // implementing kafka.
//        kafkaTemplate.send("anuj-events","Kafkaaaaaaa");


        ServiceInstance notificationService = discoveryClient.getInstances("NotificationAndDocumentService").get(0);
        URI uri = URI.create(notificationService.getUri().toString()+"/email/sendEmail");
        System.out.println("Notification Service url is : "+uri);
        try{
            emailResponse = restClient.post()
                    .uri(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(json)
                    .retrieve()
                    .body(String.class);
        }catch(Exception e){
            e.printStackTrace();
        }


        return response;
    }

    private String getMailUsingUserId(String userId) {
        return userResidentDatabaseService.getNameFromUserId(userId);
    }

    private String getEmailUsingUserId(String userId) {
        return userResidentDatabaseService.getEmailFromUserId(userId);
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

    private String validateRequest(String name, String email, String mobile, String status,String username,String password) {
        String retMsg = "200";
        if(name.isEmpty() || email.isEmpty() || mobile.isEmpty() || username.isEmpty() || password.isEmpty()){
            retMsg = "Name, Email, Mobile, Username and Password are mandatory fields.";
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


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userResidentDatabaseService.getUserByUserName(username);
//    }
}
