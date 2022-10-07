package com.example.baitapvietis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> notFoundException(NotFoundException ex, WebRequest request){
        ErrorMessage message = new ErrorMessage(
        HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getDescription(false)
                );
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }



}
