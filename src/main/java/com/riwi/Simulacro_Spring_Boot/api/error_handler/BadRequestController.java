package com.riwi.Simulacro_Spring_Boot.api.error_handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.riwi.Simulacro_Spring_Boot.api.dto.response.erros.BaseErrorResp;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.erros.ErrorsResp;
import com.riwi.Simulacro_Spring_Boot.util.exceptions.BadRequestException;

/**
 * BadRequestController
 */
@RestControllerAdvice
public class BadRequestController {

    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseErrorResp handleBadRequest(MethodArgumentNotValidException exception){

        List<Map<String,String>> errors = new ArrayList<>();

        /*
         * getBindingResult obtiene los resultados con el fiel y el error
         * getFieldErrors obtiene la lista de los nombres del campo del error 
         */
        exception.getBindingResult().getFieldErrors().forEach(e -> {
            Map<String,String> error = new HashMap<>();
            error.put("error", e.getDefaultMessage()); //agregar al map el error
            error.put("field", e.getField()); //agregar al map en donde ocurrió el error
            errors.add(error);
        });


        return ErrorsResp.builder()
                .code(HttpStatus.BAD_REQUEST.value()) //400
                .status(HttpStatus.BAD_REQUEST.name()) //BAD_REQUEST
                .errors(errors) // [ { "field": "mal", "error": "mal"} ]
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    public BaseErrorResp handleBadRequest(BadRequestException exception){
        List<Map<String,String>> errors = new ArrayList<>();

        Map<String,String> error = new HashMap<>();
        
        error.put("field", exception.getMessage());

        errors.add(error);

        
        return ErrorsResp.builder()
                .code(HttpStatus.BAD_REQUEST.value()) //400
                .status(HttpStatus.BAD_REQUEST.name()) //BAD_REQUEST
                .errors(errors) // [ { "field": "mal", "error": "mal"} ]
                .build();

    }
    
}   