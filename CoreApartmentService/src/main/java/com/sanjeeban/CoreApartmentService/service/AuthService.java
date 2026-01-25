package com.sanjeeban.CoreApartmentService.service;


import com.netflix.discovery.converters.Auto;
import com.sanjeeban.CoreApartmentService.customException.CustomAuthenticationException;
import com.sanjeeban.CoreApartmentService.dataAccessLayer.UserResidentDatabaseService;
import com.sanjeeban.CoreApartmentService.dto.LoginRequest;
import com.sanjeeban.CoreApartmentService.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {


    @Autowired
    UserResidentDatabaseService userResidentDatabaseService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public String loggingIn(LoginRequest request) {

        String userId = request.getUserId();
        String userName = request.getUsername();

        Authentication authentication = null;

        try{
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userName,
                            request.getPassword()
                    )
            );
        }catch (Exception e){
            throw new CustomAuthenticationException("There is error in authentication");
        }



//        UserAccount user = (UserAccount) authentication.getPrincipal();
        boolean authenticationStatus = authentication.isAuthenticated();
        UserAccount user = null;
        try{
            user = userResidentDatabaseService.getUser(Long.valueOf(userId));
        }catch(Exception e){
            throw new BadCredentialsException("User id is not valid.");
        }

        String token = jwtService.generateToken(user);



        return token;
    }
}
