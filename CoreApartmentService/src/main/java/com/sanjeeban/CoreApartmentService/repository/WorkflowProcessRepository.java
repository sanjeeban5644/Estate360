package com.sanjeeban.CoreApartmentService.repository;

import com.sanjeeban.CoreApartmentService.entity.WorkflowProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkflowProcessRepository extends JpaRepository<WorkflowProcess, Long> {

    @Query("select t from WorkflowProcess t where t.complaintId = :complaintId")
    public Optional<WorkflowProcess> getWorkflowDetailsFromComplaintId(Long complaintId);

}

