package com.example.msscbeerservice.exceptions;

import com.example.msscbeerservice.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.Arrays;

@RestControllerAdvice
public class BreweryExceptionHandler {

    @ExceptionHandler(BreweryException.class)
    public ResponseEntity<ErrorMessage> exceptionHandler(BreweryException e) {
        String[] errors = e.getMessage().split(",");
        return new ResponseEntity<>(ErrorMessage.builder()
                .messages(Arrays.asList(errors))
                .time(OffsetDateTime.now())
                .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> exceptionHandler(Exception e) {
        String[] errors = e.getMessage().split(",");
        return new ResponseEntity<>(ErrorMessage.builder()
                .messages(Arrays.asList(errors))
                .time(OffsetDateTime.now())
                .build(),
                HttpStatus.BAD_REQUEST);
    }
}
