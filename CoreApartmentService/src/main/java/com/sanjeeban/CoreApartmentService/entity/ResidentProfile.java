package com.sanjeeban.CoreApartmentService.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "t_resident_profile", schema = "estatedb")
public class ResidentProfile extends AuditableEntity{
    @Id
    @Column(name = "resident_id", length = 10)
    private Long residentId;

    @Column(name = "user_id", length = 10)
    private Long userId;

    @Column(name = "flat_id", length = 10)
    private Long flatId;

    @Column(name = "resident_grp_id", length = 10)
    private Long residentGrpId;

    @Column(name = "resident_type", length = 20)
    private String residentType;

    @Column(name = "dob")
    private java.sql.Date dob;

    @Column(name = "aadhar", length = 20)
    private String aadhar;

    @Column(name = "previous_address", length = 200)
    private String previousAddress;

    @Column(name = "current_address", length = 200)
    private String currentAddress;
}
