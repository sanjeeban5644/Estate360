package com.sanjeeban.CoreApartmentService.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_workflow_status_master", schema = "estatedb")
public class WorkflowStatusMaster extends AuditableEntity{

    @Id
    @Column(name = "status_id", length = 20)
    private Long statusId;

    @Column(name="status_code", length=20)
    private String statusCode;

    @Column(name="status_desc", length=100)
    private String statusDescription;

    public WorkflowStatusMaster() {
    }

    public WorkflowStatusMaster(Long statusId, String statusCode, String statusDescription) {
        this.statusId = statusId;
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
}
