package com.sanjeeban.CoreApartmentService.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "t_workflow_history", schema = "estatedb")
public class WorkflowHistory extends AuditableEntity{
    @Id
    @Column(name = "history_id", length = 10)
    private Long historyId;

    @Column(name = "complaint_id", length = 10)
    private Long complaintId;

    @Column(name = "status_id", length = 10)
    private Long statusId;

    @Column(name = "changed_by", length = 10)
    private Long changedBy;

    @Column(name = "changed_at")
    private java.sql.Timestamp changedAt;
}
