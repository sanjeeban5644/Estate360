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
public class UserAccount extends AuditableEntity{

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


    public UserAccount() {
    }

    public UserAccount(Long userId, String userName, String email, String mobile, String status) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.mobile = mobile;
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
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
}
