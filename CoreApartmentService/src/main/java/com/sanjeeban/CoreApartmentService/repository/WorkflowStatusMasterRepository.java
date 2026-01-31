package com.sanjeeban.CoreApartmentService.repository;

import com.sanjeeban.CoreApartmentService.entity.WorkflowStatusMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowStatusMasterRepository extends JpaRepository<WorkflowStatusMaster, Long> {

    @Query("select t.statusCode from WorkflowStatusMaster t where t.statusId = :statusId")
    public String getStatuscodeFromStatusId(Long statusId);


}
