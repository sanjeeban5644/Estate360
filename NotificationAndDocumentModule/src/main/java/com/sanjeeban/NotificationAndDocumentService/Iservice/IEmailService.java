package com.sanjeeban.NotificationAndDocumentService.Iservice;

public interface IEmailService {

    public String sendEmail(String sendTo, String sendSubject, String body, String sendFrom);

    public void sendEmailWithHtml();
    public void sendEmailWithAttachment();
}
