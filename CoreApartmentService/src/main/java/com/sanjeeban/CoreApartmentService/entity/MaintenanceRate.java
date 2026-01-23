package com.sanjeeban.CoreApartmentService.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;




@Entity
@Table(name = "t_maintenance_rate", schema = "estatedb")
public class MaintenanceRate extends AuditableEntity{
    @Id
    @Column(name = "rate_id", length = 10)
    private Long rateId;

    @Column(name = "bhk_type", length = 10)
    private String bhkType;

    @Column(name = "base_amount")
    private Double baseAmount;
}

