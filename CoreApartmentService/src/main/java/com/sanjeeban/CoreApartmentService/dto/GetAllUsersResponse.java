package com.sanjeeban.CoreApartmentService.dto;

public class GetAllUsersResponse {
    private String name;
    private String email;
    private String mobile;
    private String status;
    private String userId;

    public GetAllUsersResponse() {
    }

    public GetAllUsersResponse(String name, String email, String mobile, String status, String userId) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.status = status;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
