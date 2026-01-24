package com.sanjeeban.CoreApartmentService.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Table(
        name = "t_user_account",
        schema="estatedb"
)
public class UserAccount extends AuditableEntity implements UserDetails {

    @Id
    @Column(name="user_id",length = 10)
    private Long userId;

    @Column(name="name",length=100)
    private String name;

    @Column(name="email",length=100)
    private String email;

    @Column(name="mobile",length=10)
    private String mobile;

    @Column(name="status")
    private String status;

    @Column(name="user_name")
    private String userName;

    @Column(name="password")
    private String password;

    public UserAccount() {
    }

    public UserAccount(Long userId, String name, String email, String mobile, String status, String userName, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.status = status;
        this.userName = userName;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }
}
