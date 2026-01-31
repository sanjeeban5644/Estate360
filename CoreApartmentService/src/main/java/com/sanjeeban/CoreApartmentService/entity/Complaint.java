package com.sanjeeban.CoreApartmentService.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_complaint", schema = "estatedb")
public class Complaint extends AuditableEntity{
    @Id
    @Column(name = "complaint_id", length = 25)
    private Long complaintId;

    @Column(name = "resident_id", length = 10)
    private Long residentId;

    @Column(name = "complaint_type_id", length = 10)
    private Long complaintTypeId;

    @Column(name = "description", length = 500)
    private String description;

    public Complaint() {
    }

    public Complaint(Long complaintId, Long residentId, Long complaintTypeId, String description) {
        this.complaintId = complaintId;
        this.residentId = residentId;
        this.complaintTypeId = complaintTypeId;
        this.description = description;
    }

    public Long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }

    public Long getResidentId() {
        return residentId;
    }

    public void setResidentId(Long residentId) {
        this.residentId = residentId;
    }

    public Long getComplaintTypeId() {
        return complaintTypeId;
    }

    public void setComplaintTypeId(Long complaintTypeId) {
        this.complaintTypeId = complaintTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
