package com.sanjeeban.CoreApartmentService.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "t_flat", schema = "estatedb")
public class Flat extends AuditableEntity{
    @Id
    @Column(name = "flat_no", length = 10)
    private String flatNo;

    @Column(name = "bhk_type", length = 10)
    private String bhkType;

    @Column(name = "carpet_area")
    private Double carpetArea;
}
