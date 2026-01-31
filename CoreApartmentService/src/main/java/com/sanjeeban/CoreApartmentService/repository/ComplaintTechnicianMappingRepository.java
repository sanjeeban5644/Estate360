package com.sanjeeban.CoreApartmentService.repository;


import com.sanjeeban.CoreApartmentService.entity.ComplaintTechnicianMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintTechnicianMappingRepository extends JpaRepository<ComplaintTechnicianMapping,Long> {
}
