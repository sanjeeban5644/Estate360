package com.sanjeeban.CoreApartmentService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_role_master", schema = "estatedb")
public class RoleMaster extends AuditableEntity{
    @Id
    @Column(name = "role_id", length = 10)
    private Long roleId;

    @Column(name = "role_name", length = 50)
    private String roleName;

    public RoleMaster() {
    }

    public RoleMaster(Long roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
