package com.sanjeeban.CoreApartmentService.customException;

public class NameDoesNotExistsException extends RuntimeException{
    public NameDoesNotExistsException(String msg){super(msg);}
}
