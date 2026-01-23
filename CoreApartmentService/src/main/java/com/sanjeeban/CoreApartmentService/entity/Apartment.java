package com.sanjeeban.CoreApartmentService.entity;
import jakarta.persistence.*;


@Entity
@Table(name = "t_apartment", schema = "estatedb")
public class Apartment extends AuditableEntity{

    @EmbeddedId
    private ApartmentPrimaryKey apartmentPrimaryKey;

    @Column(name = "bhk_type", length = 10)
    private String bhkType;

    @Column(name = "carpet_area")
    private Double carpetArea;

    @Column(name = "availability")
    private Boolean availability;


    public Apartment() {
    }

    public Apartment(ApartmentPrimaryKey apartmentPrimaryKey, String bhkType, Double carpetArea, Boolean availability) {
        this.apartmentPrimaryKey = apartmentPrimaryKey;
        this.bhkType = bhkType;
        this.carpetArea = carpetArea;
        this.availability = availability;
    }

    public ApartmentPrimaryKey getApartmentPrimaryKey() {
        return apartmentPrimaryKey;
    }

    public void setApartmentPrimaryKey(ApartmentPrimaryKey apartmentPrimaryKey) {
        this.apartmentPrimaryKey = apartmentPrimaryKey;
    }

    public String getBhkType() {
        return bhkType;
    }

    public void setBhkType(String bhkType) {
        this.bhkType = bhkType;
    }

    public Double getCarpetArea() {
        return carpetArea;
    }

    public void setCarpetArea(Double carpetArea) {
        this.carpetArea = carpetArea;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
}

