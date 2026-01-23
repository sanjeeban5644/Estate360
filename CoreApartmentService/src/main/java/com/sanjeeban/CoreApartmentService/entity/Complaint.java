package com.sanjeeban.CoreApartmentService.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_complaint", schema = "estatedb")
public class Complaint {
    @Id
    @Column(name = "complaint_id", length = 10)
    private Long complaintId;

    @Column(name = "resident_id", length = 10)
    private Long residentId;

    @Column(name = "complaint_type_id", length = 10)
    private Long complaintTypeId;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "created_at")
    private java.sql.Timestamp createdAt;

}
