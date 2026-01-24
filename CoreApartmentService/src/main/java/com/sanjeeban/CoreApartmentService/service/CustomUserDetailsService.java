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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userResidentDatabaseService.getUserName(username);

        Long userId = userAccount.getUserId();

        String role = userResidentDatabaseService.getRoleFromUserId(userId);

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+userResidentDatabaseService.getRoleFromUserId(userId));

        return new User(
                userAccount.getUserName(),
                userAccount.getPassword(),
                Collections.singletonList(authority));
        }

    }

