package com.bqt.ecommerce.exceptions;


public class BadRequestException extends RuntimeException{
    String message;

    public BadRequestException(String message){

        super(message);


    }
}
