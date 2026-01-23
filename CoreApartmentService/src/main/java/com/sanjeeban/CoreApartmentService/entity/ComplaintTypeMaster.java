package com.sanjeeban.CoreApartmentService.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "t_complaint_type_master", schema = "estatedb")
public class ComplaintTypeMaster {
    @Id
    @Column(name = "complaint_type_id", length = 10)
    private Long complaintTypeId;

    @Column(name = "complaint_code", length = 20)
    private String complaintCode;

    @Column(name = "complaint_desc", length = 200)
    private String complaintDesc;
}
