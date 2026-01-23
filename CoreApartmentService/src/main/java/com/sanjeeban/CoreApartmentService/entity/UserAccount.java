package com.sanjeeban.CoreApartmentService.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(
        name = "t_user_account",
        schema="estatedb"
)
public class UserAccount {

    @Id
    @Column(name="user_id",length = 10)
    private Long userId;

    @Column(name="user_name",length=50)
    private String userName;

    @Column(name="email",length=20)
    private String email;

    @Column(name="mobile",length=10)
    private String mobile;

    @Column(name="status")
    private String status;


}
