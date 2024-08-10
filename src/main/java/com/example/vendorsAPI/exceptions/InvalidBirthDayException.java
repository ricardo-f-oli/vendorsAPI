package com.example.vendorsAPI.exceptions;

public class InvalidBirthDayException extends IllegalArgumentException{
    public InvalidBirthDayException(String message) {
        super(message);
    }
}
