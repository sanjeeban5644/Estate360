package com.sanjeeban.CoreApartmentService.controller;

import com.sanjeeban.CoreApartmentService.dto.EmailRequest;
import com.sanjeeban.CoreApartmentService.dto.SaveNewResidentRequest;
import com.sanjeeban.CoreApartmentService.dto.SaveUserResidentResponse;
import com.sanjeeban.CoreApartmentService.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {


    @Autowired
    EmailService emailService;

    @Operation(summary = "This is email sender",description = "This is email sender")
    @PostMapping(path="/sendEmail")
    public String sendEmail(@RequestBody EmailRequest request){
        emailService.sendEmail(request.getSendTo(),request.getSendSubject(),request.getBody(),request.getSendFrom());
        return "sent email";
    }

}
