package com.sanjeeban.CoreApartmentService.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ApartmentPrimaryKey {

    @Column(name = "apartment_id", length = 10)
    private Long apartmentId;

    @Column(name = "block_no", length = 10)
    private String blockNo;

    @Column(name = "flat_no", length = 10)
    private String flatNo;

    public ApartmentPrimaryKey() {
    }

    public ApartmentPrimaryKey(Long apartmentId, String blockNo, String flatNo) {
        this.apartmentId = apartmentId;
        this.blockNo = blockNo;
        this.flatNo = flatNo;
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
}
