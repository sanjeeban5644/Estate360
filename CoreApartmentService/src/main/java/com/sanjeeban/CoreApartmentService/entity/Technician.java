package com.sanjeeban.CoreApartmentService.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_technician", schema = "estatedb")
public class Technician extends AuditableEntity{
    @Id
    @Column(name = "tech_id", length = 10)
    private Long techId;

    @Column(name = "tech_name", length = 50)
    private String techName;

    @Column(name = "tech_mobile", length = 15)
    private String techMobile;
}
