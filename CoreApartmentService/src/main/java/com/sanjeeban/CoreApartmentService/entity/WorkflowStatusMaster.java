package com.sanjeeban.CoreApartmentService.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_workflow_status_master", schema = "estatedb")
public class WorkflowStatusMaster {

    @Id
    @Column(name = "status_id", length = 10)
    private Long statusId;

    @Column(name="status_code", length=20)
    private String statusCode;

    @Column(name="status_desc", length=100)
    private String statusDescription;
}
