package com.sanjeeban.CoreApartmentService.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_technician_skill", schema = "estatedb")
public class TechnicianSkill extends AuditableEntity{
    @Id
    @Column(name = "tech_id", length = 10)
    private Long techId;

    @Column(name = "complaint_type_id", length = 10)
    private Long complaintTypeId;
}

