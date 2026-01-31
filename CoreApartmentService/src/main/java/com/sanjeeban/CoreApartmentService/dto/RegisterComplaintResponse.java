package com.sanjeeban.CoreApartmentService.dto;

public class RegisterComplaintResponse extends GeneralResponse{
    private String complaintId;
    private String currentStatus;
    private String emailSent;

    public RegisterComplaintResponse() {
    }

    public RegisterComplaintResponse(String responseCode, String responseMsg, String remarks, String complaintId, String currentStatus, String emailSent) {
        super(responseCode, responseMsg, remarks);
        this.complaintId = complaintId;
        this.currentStatus = currentStatus;
        this.emailSent = emailSent;
    }

    public String getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(String complaintId) {
        this.complaintId = complaintId;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getEmailSent() {
        return emailSent;
    }

    public void setEmailSent(String emailSent) {
        this.emailSent = emailSent;
    }
}
