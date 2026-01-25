package com.sanjeeban.CoreApartmentService.service;

import com.sanjeeban.CoreApartmentService.dataAccessLayer.UserResidentDatabaseService;
import com.sanjeeban.CoreApartmentService.entity.UserAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
//
//public class CustomUserDetails implements UserDetails {
//
//    private final UserAccount userAccount;
//    private final UserResidentDatabaseService userResidentDatabaseService;
//
//
//    public CustomUserDetails(UserAccount userAccount, UserResidentDatabaseService userResidentDatabaseService) {
//        this.userAccount = userAccount;
//        this.userResidentDatabaseService = userResidentDatabaseService;
//    }
//
//    public UserAccount getUserAccount() {
//        return userAccount;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Long userId = userAccount.getUserId();
//        String role = userResidentDatabaseService.getRoleFromUserId(userId);
//        return List.of(
//                new SimpleGrantedAuthority("ROLE_"+role)
//        );
//    }
//
//    @Override
//    public String getPassword() {
//        return userAccount.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return userAccount.getUserName();
//    }
//
//    @Override public boolean isAccountNonExpired() { return true; }
//    @Override public boolean isAccountNonLocked() { return true; }
//    @Override public boolean isCredentialsNonExpired() { return true; }
//    @Override public boolean isEnabled() { return true; }
//}
