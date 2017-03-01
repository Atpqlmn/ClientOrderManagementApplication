package com.application.management.order.client.controllers;


import com.application.management.order.client.error.JsonError;
import com.application.management.order.client.exception.CustomException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@RestController
public class GlobalControllerExceptionHandler {


    @ExceptionHandler({CustomException.class})
    public ResponseEntity<?> handleCustomException(HttpServletRequest request, Throwable ex) {
        return new ResponseEntity<>(new JsonError(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
