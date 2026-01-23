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

    @Column(name = "updated_at")
    private java.sql.Timestamp updatedAt;
}

