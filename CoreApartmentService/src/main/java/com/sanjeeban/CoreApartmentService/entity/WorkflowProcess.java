package com.sanjeeban.CoreApartmentService.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_workflow_process", schema = "estatedb")
public class WorkflowProcess extends AuditableEntity{
    @Id
    @Column(name = "workflow_id", length = 10)
    private Long workflowId;

    @Column(name = "complaint_id", length = 10)
    private Long complaintId;

    @Column(name = "status_id", length = 10)
    private Long statusId;

    public WorkflowProcess() {
    }

    public WorkflowProcess(Long workflowId, Long complaintId, Long statusId) {
        this.workflowId = workflowId;
        this.complaintId = complaintId;
        this.statusId = statusId;
    }

    public Long getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
    }

    public Long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
}

