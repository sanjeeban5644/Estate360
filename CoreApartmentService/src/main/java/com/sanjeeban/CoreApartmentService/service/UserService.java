package com.sanjeeban.CoreApartmentService.service;

import com.sanjeeban.CoreApartmentService.dataAccessLayer.UserResidentDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserResidentDatabaseService userResidentDatabaseService;


    @Autowired
    public UserService(UserResidentDatabaseService userResidentDatabaseService){
        this.userResidentDatabaseService = userResidentDatabaseService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userResidentDatabaseService.getUserByUserName(username);
    }
}
