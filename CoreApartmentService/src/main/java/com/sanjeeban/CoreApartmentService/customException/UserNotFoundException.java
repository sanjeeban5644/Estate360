package com.sanjeeban.CoreApartmentService.customException;

import org.apache.catalina.User;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String msg){ super(msg); }
}
