package com.example.baitapvietis.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadRequstException extends RuntimeException{

    public BadRequstException(String message){
        super(message);
    }
}
