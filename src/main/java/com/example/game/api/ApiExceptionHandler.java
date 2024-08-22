package com.example.game.api;

import com.example.game.api.model.exception.GameCompletedException;
import com.example.game.api.model.exception.GameNotFoundException;
import com.example.game.api.model.exception.UnknownFigureException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {GameCompletedException.class, UnknownFigureException.class})
    protected ResponseEntity<Object> handleGameCompleted(
            RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value
            = {GameNotFoundException.class})
    protected ResponseEntity<Object> handleGameNotFound(
            RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
