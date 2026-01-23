package com.sanjeeban.CoreApartmentService.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "t_user_role", schema = "estatedb")
public class UserRole {
    @Id
    @Column(name = "user_id", length = 10)
    private Long userId;

    @Column(name = "role_id", length = 10)
    private Long roleId;
}
