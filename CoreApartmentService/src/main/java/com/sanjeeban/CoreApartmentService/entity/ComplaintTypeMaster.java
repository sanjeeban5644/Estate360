package com.sanjeeban.CoreApartmentService.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "t_complaint_type_master", schema = "estatedb")
public class ComplaintTypeMaster extends AuditableEntity{
    @Id
    @Column(name = "complaint_type_id", length = 10)
    private Long complaintTypeId;

    @Column(name = "complaint_code", length = 20)
    private String complaintCode;

    @Column(name = "complaint_desc", length = 200)
    private String complaintDesc;

    public ComplaintTypeMaster() {
    }

    public ComplaintTypeMaster(Long complaintTypeId, String complaintCode, String complaintDesc) {
        this.complaintTypeId = complaintTypeId;
        this.complaintCode = complaintCode;
        this.complaintDesc = complaintDesc;
    }

    public Long getComplaintTypeId() {
        return complaintTypeId;
    }

    public void setComplaintTypeId(Long complaintTypeId) {
        this.complaintTypeId = complaintTypeId;
    }

    public String getComplaintCode() {
        return complaintCode;
    }

    public void setComplaintCode(String complaintCode) {
        this.complaintCode = complaintCode;
    }

    public String getComplaintDesc() {
        return complaintDesc;
    }

    public void setComplaintDesc(String complaintDesc) {
        this.complaintDesc = complaintDesc;
    }
}
