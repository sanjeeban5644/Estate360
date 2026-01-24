package com.sanjeeban.CoreApartmentService.dto;

public class SaveNewUserRequest {
    private String name;
    private String email;
    private String mobile;
    private String role;

    private String userName;

    private String password;

    public SaveNewUserRequest() {
    }

    public SaveNewUserRequest(String name, String email, String mobile, String role, String userName, String password) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.role = role;
        this.userName = userName;
        this.password = password;
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


    public String getRole() {
        return role;
    }

    public void setRole(String userCode) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
