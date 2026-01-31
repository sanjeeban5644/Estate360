package com.sanjeeban.CoreApartmentService.repository;

import com.sanjeeban.CoreApartmentService.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    @Query("SELECT t FROM Apartment t WHERE t.apartmentId = :apartmentId")
    public Optional<Apartment> findByApartmentId(@Param("apartmentId") Long apartmentId);

}

