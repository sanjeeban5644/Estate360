package com.sanjeeban.NotificationAndDocumentService.service;


import com.sanjeeban.NotificationAndDocumentService.Iservice.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

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