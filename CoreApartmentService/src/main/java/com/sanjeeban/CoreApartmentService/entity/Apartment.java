package com.sanjeeban.CoreApartmentService.entity;
import jakarta.persistence.*;


@Entity
@Table(name = "t_apartment", schema = "estatedb")
public class Apartment extends AuditableEntity{

    @Id
    @Column(name = "apartment_id", length = 10)
    private Long apartmentId;

    @Column(name = "block_no", length = 10)
    private String blockNo;

    @Column(name = "flat_no", length = 10)
    private String flatNo;

    @Column(name = "bhk_type", length = 10)
    private String bhkType;

    @Column(name = "carpet_area")
    private Double carpetArea;

    @Column(name = "availability")
    private Boolean availability;


    public Apartment() {
    }

    public Apartment(Long apartmentId, String blockNo, String flatNo, String bhkType, Double carpetArea, Boolean availability) {
        this.apartmentId = apartmentId;
        this.blockNo = blockNo;
        this.flatNo = flatNo;
        this.bhkType = bhkType;
        this.carpetArea = carpetArea;
        this.availability = availability;
    }

    public Long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getBlockNo() {
        return blockNo;
    }

    public void setBlockNo(String blockNo) {
        this.blockNo = blockNo;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
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

