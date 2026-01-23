package com.sanjeeban.CoreApartmentService.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;
import java.time.LocalDate;


@Entity
@Table(name = "t_resident_profile", schema = "estatedb")
public class ResidentProfile extends AuditableEntity{

    @Id
    @Column(name = "resident_id", length = 10)
    private Long residentId;

    @Column(name = "user_id", length = 10)
    private Long userId;

    @Column(name = "apartment_id", length = 10)
    private Long apartmentId;

    @Column(name = "resident_grp_id", length = 10)
    private Long residentGrpId;

    @Column(name = "resident_type", length = 20)
    private String residentType;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "aadhar", length = 20)
    private String aadhar;

    @Column(name = "previous_address", length = 200)
    private String previousAddress;

    @Column(name = "current_address", length = 200)
    private String currentAddress;

    public ResidentProfile() {
    }

    public ResidentProfile(Long residentId, Long userId, Long apartmentId, Long residentGrpId, String residentType, LocalDate dob, String aadhar, String previousAddress, String currentAddress) {
        this.residentId = residentId;
        this.userId = userId;
        this.apartmentId = apartmentId;
        this.residentGrpId = residentGrpId;
        this.residentType = residentType;
        this.dob = dob;
        this.aadhar = aadhar;
        this.previousAddress = previousAddress;
        this.currentAddress = currentAddress;
    }

    public Long getResidentId() {
        return residentId;
    }

    public void setResidentId(Long residentId) {
        this.residentId = residentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Long flatId) {
        this.apartmentId = flatId;
    }

    public Long getResidentGrpId() {
        return residentGrpId;
    }

    public void setResidentGrpId(Long residentGrpId) {
        this.residentGrpId = residentGrpId;
    }

    public String getResidentType() {
        return residentType;
    }

    public void setResidentType(String residentType) {
        this.residentType = residentType;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getPreviousAddress() {
        return previousAddress;
    }

    public void setPreviousAddress(String previousAddress) {
        this.previousAddress = previousAddress;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }
}
