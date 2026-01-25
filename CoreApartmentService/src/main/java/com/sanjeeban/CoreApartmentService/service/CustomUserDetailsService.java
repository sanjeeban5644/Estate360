package com.sanjeeban.CoreApartmentService.service;

import com.sanjeeban.CoreApartmentService.dataAccessLayer.UserResidentDatabaseService;
import com.sanjeeban.CoreApartmentService.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserResidentDatabaseService userResidentDatabaseService;


    @Autowired
    public CustomUserDetailsService(UserResidentDatabaseService userResidentDatabaseService){
        this.userResidentDatabaseService = userResidentDatabaseService;
    }





    @Override
    public UserDetails loadUserByUsername(String username) {
        UserAccount user = userResidentDatabaseService.getUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        String role = userResidentDatabaseService.getRoleFromUserId(user.getUserId());

        return User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .roles(role) // Spring auto-adds ROLE_
                .build();

        //return new CustomUserDetails(user,null);
    }

    }

