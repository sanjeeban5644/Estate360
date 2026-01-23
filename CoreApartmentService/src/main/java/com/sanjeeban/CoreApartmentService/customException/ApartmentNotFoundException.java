package com.sanjeeban.CoreApartmentService.customException;


public class ApartmentNotFoundException extends RuntimeException{
    public ApartmentNotFoundException(String msg){super(msg);}
}
