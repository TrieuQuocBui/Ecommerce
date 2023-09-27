package com.bqt.ecommerce.exceptions;

public class NotFoundException extends RuntimeException{
    String message;

    public NotFoundException(String message){

        super(message);


    }
}
