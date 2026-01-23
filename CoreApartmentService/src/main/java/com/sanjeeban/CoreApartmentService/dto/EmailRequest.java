package com.sanjeeban.CoreApartmentService.dto;

public class EmailRequest {
    private String sendTo;
    private String sendSubject;
    private String body;
    private String sendFrom;

    public EmailRequest() {
    }

    public EmailRequest(String sendTo, String sendSubject, String body, String sendFrom) {
        this.sendTo = sendTo;
        this.sendSubject = sendSubject;
        this.body = body;
        this.sendFrom = sendFrom;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public String getSendSubject() {
        return sendSubject;
    }

    public void setSendSubject(String sendSubject) {
        this.sendSubject = sendSubject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSendFrom() {
        return sendFrom;
    }

    public void setSendFrom(String sendFrom) {
        this.sendFrom = sendFrom;
    }
}

