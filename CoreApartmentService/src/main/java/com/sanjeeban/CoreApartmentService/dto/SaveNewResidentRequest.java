package com.sanjeeban.CoreApartmentService.dto;

import javax.xml.crypto.Data;

public class SaveNewResidentRequest {

    private String userId;
    private String apartmentId;
    private String residentGroupId;
    private String residentType;
    private String dob;
    private String aadhar;
    private String prevAddress;
    private String currentAddress;

    public SaveNewResidentRequest() {
    }

    public SaveNewResidentRequest(String userId, String apartmentId, String residentGroupId, String residentType, String dob, String aadhar, String prevAddress, String currentAddress) {
        this.userId = userId;
        this.apartmentId = apartmentId;
        this.residentGroupId = residentGroupId;
        this.residentType = residentType;
        this.dob = dob;
        this.aadhar = aadhar;
        this.prevAddress = prevAddress;
        this.currentAddress = currentAddress;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(String apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getResidentGroupId() {
        return residentGroupId;
    }

    public void setResidentGroupId(String residentGroupId) {
        this.residentGroupId = residentGroupId;
    }

    public String getResidentType() {
        return residentType;
    }

    public void setResidentType(String residentType) {
        this.residentType = residentType;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getPrevAddress() {
        return prevAddress;
    }

    public void setPrevAddress(String prevAddress) {
        this.prevAddress = prevAddress;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }
}
