package com.bqt.ecommerce.exceptions;

public class UnauthorizedException extends RuntimeException{
    String message;

    public UnauthorizedException(String message){
        super(message);

    }
}
