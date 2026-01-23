package com.sanjeeban.CoreApartmentService.Iservice;


public interface IEmailService {

    void sendEmail(String to, String subject, String body,String from);

    void sendEmailWithHtml();

    void sendEmailWithAttachment();
}
