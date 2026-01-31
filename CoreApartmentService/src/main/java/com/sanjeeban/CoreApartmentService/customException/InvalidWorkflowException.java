package com.sanjeeban.CoreApartmentService.customException;

public class InvalidWorkflowException extends RuntimeException{
    public InvalidWorkflowException(String msg){
        super(msg);
    }
}
