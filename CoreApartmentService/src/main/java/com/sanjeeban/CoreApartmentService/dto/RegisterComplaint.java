package com.sanjeeban.CoreApartmentService.dto;

public class RegisterComplaint {
    private String residentId;
    private String apartmentId;
    private String complaintCode;
    private String complaintDescription;

    public RegisterComplaint() {
    }

    public RegisterComplaint(String residentId, String apartmentId, String complaintCode, String complaintDescription) {
        this.residentId = residentId;
        this.apartmentId = apartmentId;
        this.complaintCode = complaintCode;
        this.complaintDescription = complaintDescription;
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

    public String getComplaintCode() {
        return complaintCode;
    }

    public void setComplaintCode(String complaintCode) {
        this.complaintCode = complaintCode;
    }

    public String getComplaintDescription() {
        return complaintDescription;
    }

    public void setComplaintDescription(String complaintDescription) {
        this.complaintDescription = complaintDescription;
    }
}
