package com.sanjeeban.CoreApartmentService.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;




@Entity
@Table(name = "t_apartment", schema = "estatedb")
public class Apartment {
    @Id
    @Column(name = "apartment_id", length = 10)
    private Long apartmentId;

    @Column(name = "block_no", length = 10)
    private String blockNo;

    @Column(name = "flat_no", length = 10)
    private String flatNo;
}

