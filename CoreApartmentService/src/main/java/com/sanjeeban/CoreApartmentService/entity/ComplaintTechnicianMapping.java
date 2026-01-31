package com.sanjeeban.CoreApartmentService.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "t_complaint_technician_mapping", schema = "estatedb")
public class ComplaintTechnicianMapping extends AuditableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mapping_id")
    private Long mappingId;

    @Column(name="technician_id")
    private Long technicianId;

    @Column(name="complaint_id")
    private Long complaintId;


    public ComplaintTechnicianMapping() {
    }

    public ComplaintTechnicianMapping(Long mappingId, Long technicianId, Long complaintId) {
        this.mappingId = mappingId;
        this.technicianId = technicianId;
        this.complaintId = complaintId;
    }

    public Long getMappingId() {
        return mappingId;
    }

    public void setMappingId(Long mappingId) {
        this.mappingId = mappingId;
    }

    public Long getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(Long technicianId) {
        this.technicianId = technicianId;
    }

    public Long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }
}
