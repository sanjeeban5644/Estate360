package com.sanjeeban.CoreApartmentService.repository;

import com.sanjeeban.CoreApartmentService.entity.ComplaintTypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComplaintTypeMasterRepository extends JpaRepository<ComplaintTypeMaster, Long> {

    @Query("select t from ComplaintTypeMaster t where t.complaintCode = :complaintCode")
    public Optional<ComplaintTypeMaster> getComplaintByComplaintCode(String complaintCode);

}

