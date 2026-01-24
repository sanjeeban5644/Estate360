package com.sanjeeban.CoreApartmentService.repository;

import com.sanjeeban.CoreApartmentService.entity.RoleMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.Optional;

@Repository
public interface RoleMasterRepository extends JpaRepository<RoleMaster, Long> {

    @Query("select t from RoleMaster t where t.roleName = :roleName")
    public RoleMaster getRoleByRoleName(@Param("roleName") String roleName);
}

