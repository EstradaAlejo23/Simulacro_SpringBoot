package com.riwi.Simulacro_Spring_Boot.util.exceptions;

public class BadRequestException extends RuntimeException {
    
    public BadRequestException(String message){
        super(message);
    }

    // throw new BadRequestException("Error ");
}



