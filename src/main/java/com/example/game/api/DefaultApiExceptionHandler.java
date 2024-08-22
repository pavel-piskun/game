package com.example.game.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DefaultApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value
            = {RuntimeException.class})
    protected ResponseEntity<Object> handleInternalError(
            RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>("Server internal error, please check the logs.", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
