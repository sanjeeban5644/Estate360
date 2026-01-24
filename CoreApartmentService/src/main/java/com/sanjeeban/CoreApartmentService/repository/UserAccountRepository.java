package com.sanjeeban.CoreApartmentService.repository;

import com.sanjeeban.CoreApartmentService.entity.UserAccount;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    @Query("select t from UserAccount t where t.userId = :userId")
    public Optional<UserAccount> getUserWithUserId(@Param("userId") Long userId);

    boolean existsByUserId(Long userId);

    @Query("select t from UserAccount t where t.userName = :userName")
    Optional<UserAccount> getUserByUserName(@Param("userName") String userName);



}

