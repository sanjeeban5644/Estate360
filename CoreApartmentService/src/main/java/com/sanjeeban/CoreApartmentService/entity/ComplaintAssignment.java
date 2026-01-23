package com.sanjeeban.CoreApartmentService.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_complaint_assignment", schema = "estatedb")
public class ComplaintAssignment {
    @Id
    @Column(name = "assignment_id", length = 10)
    private Long assignmentId;

    @Column(name = "complaint_id", length = 10)
    private Long complaintId;

    @Column(name = "tech_id", length = 10)
    private Long techId;

    @Column(name = "assigned_at")
    private java.sql.Timestamp assignedAt;
}

