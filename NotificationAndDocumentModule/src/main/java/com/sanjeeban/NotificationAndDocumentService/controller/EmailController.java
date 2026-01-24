package com.sanjeeban.NotificationAndDocumentService.controller;

import com.sanjeeban.NotificationAndDocumentService.Iservice.IEmailService;
import com.sanjeeban.NotificationAndDocumentService.dto.EmailRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    IEmailService iEmailService;

    @Operation(summary = "This is email sender",description = "This is email sender")
    @PostMapping(path="/sendEmail")
    public String sendEmail(@RequestBody EmailRequest request){
        return iEmailService.sendEmail(request.getSendTo(),request.getSendSubject(),request.getBody(),request.getSendFrom());
    }

}