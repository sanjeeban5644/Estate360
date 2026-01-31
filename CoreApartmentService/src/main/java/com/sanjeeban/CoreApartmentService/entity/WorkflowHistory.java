package com.sanjeeban.CoreApartmentService.entity;
import jakarta.persistence.*;


@Entity
@Table(name = "t_workflow_history", schema = "estatedb")
public class WorkflowHistory extends AuditableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id", length = 25)
    private Long historyId;

    @Column(name = "workflow_id",length=25)
    private Long workflowId;

    @Column(name = "complaint_id", length = 25)
    private Long complaintId;

    @Column(name = "status_id", length = 10)
    private Long statusId;

    public WorkflowHistory() {
    }

    public WorkflowHistory(Long historyId, Long workflowId, Long complaintId, Long statusId) {
        this.historyId = historyId;
        this.workflowId = workflowId;
        this.complaintId = complaintId;
        this.statusId = statusId;
    }

    public Long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
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
