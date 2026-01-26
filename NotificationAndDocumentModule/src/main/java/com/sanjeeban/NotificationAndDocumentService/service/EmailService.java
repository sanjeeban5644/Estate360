package com.sanjeeban.NotificationAndDocumentService.service;


import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sanjeeban.NotificationAndDocumentService.Iservice.IEmailService;
import com.sanjeeban.NotificationAndDocumentService.dto.SaveUserResidentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    @KafkaListener(topics = "email-topic")
    public void handleEmailEvent(ObjectNode message){

        String sendTo = message.get("sendTo").asText();
        String subject = message.get("sendSubject").asText();

        System.out.println("Send mail to: " + sendTo);
    }








    @Override
    public String sendEmail(String to, String subject, String body, String from) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
//        message.setFrom("estate@gmail.com");
        javaMailSender.send(message);
        return "hello";
    }

    @Override
    public void sendEmailWithHtml() {

    }

    @Override
    public void sendEmailWithAttachment() {

    }
}