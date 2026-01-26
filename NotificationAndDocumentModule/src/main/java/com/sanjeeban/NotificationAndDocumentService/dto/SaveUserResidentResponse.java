package com.sanjeeban.NotificationAndDocumentService.dto;

public class SaveUserResidentResponse {
    private String userId;
    private String residentId;
    private String emailConfirmation;
    public SaveUserResidentResponse() {
    }

    public SaveUserResidentResponse(String userId, String residentId, String emailConfirmation) {
        super();
        this.userId = userId;
        this.residentId = residentId;
        this.emailConfirmation = emailConfirmation;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getResidentId() {
        return residentId;
    }

    public void setResidentId(String residentId) {
        this.residentId = residentId;
    }

    public String getEmailConfirmation() {
        return emailConfirmation;
    }

    public void setEmailConfirmation(String emailConfirmation) {
        this.emailConfirmation = emailConfirmation;
    }
}
