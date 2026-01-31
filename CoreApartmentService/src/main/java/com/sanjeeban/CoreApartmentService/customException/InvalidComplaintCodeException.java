package com.sanjeeban.CoreApartmentService.customException;

public class InvalidComplaintCodeException extends RuntimeException{
    public InvalidComplaintCodeException(String msg){
        super(msg);
    }
}

