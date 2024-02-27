package com.app.EpicEnergyCRM.exception;

public class UnAuthorizedException extends RuntimeException{
    public UnAuthorizedException(String message){
        super(message);
    }
}
