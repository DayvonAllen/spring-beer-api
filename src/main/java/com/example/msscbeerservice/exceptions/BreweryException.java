package com.example.msscbeerservice.exceptions;

public class BreweryException extends RuntimeException{
    public BreweryException() {
    }

    public BreweryException(String message) {
        super(message);
    }
}
