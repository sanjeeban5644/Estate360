package com.sanjeeban.CoreApartmentService.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "t_resident_maintenance", schema = "estatedb")
public class ResidentMaintenance extends AuditableEntity{
    @Id
    @Column(name = "resident_id", length = 10)
    private Long residentId;

    @Column(name = "monthly_amount")
    private Double monthlyAmount;

    @Column(name = "effective_from")
    private java.sql.Date effectiveFrom;
}
