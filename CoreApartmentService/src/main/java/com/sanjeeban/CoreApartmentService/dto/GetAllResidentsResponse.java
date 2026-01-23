package com.sanjeeban.CoreApartmentService.dto;

public class GetAllResidentsResponse {
    private String residentId;
    private String apartmentId;
    private String residentGroupId;
    private String residentType;
    private GeneralResponse response;

    public GetAllResidentsResponse() {
    }

    public GetAllResidentsResponse(String residentId, String apartmentId, String residentGroupId, String residentType, GeneralResponse response) {
        this.residentId = residentId;
        this.apartmentId = apartmentId;
        this.residentGroupId = residentGroupId;
        this.residentType = residentType;
        this.response = response;
    }

    public String getResidentId() {
        return residentId;
    }

    public void setResidentId(String residentId) {
        this.residentId = residentId;
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

    public GeneralResponse getResponse() {
        return response;
    }

    public void setResponse(GeneralResponse response) {
        this.response = response;
    }
}
