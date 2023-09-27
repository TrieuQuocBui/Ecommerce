package com.bqt.ecommerce.exceptions;

import com.bqt.ecommerce.payloads.response.MessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

// it will interfere handle process of Controller
@RestControllerAdvice
public class CustomerExceptionHandler {


    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<MessResponse> handlerBadRequestException(BadRequestException ex, WebRequest webRequest) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessResponse(ex.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<MessResponse> handlerNotFoundException(NotFoundException ex, WebRequest webRequest) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessResponse(ex.getMessage()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<MessResponse> handlerUnauthorizedException(UnauthorizedException ex, WebRequest webRequest) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessResponse(ex.getMessage()));
    }
}
