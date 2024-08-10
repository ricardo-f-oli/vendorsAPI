package com.example.vendorsAPI.exceptions;

public class InvalidDocumentException extends RuntimeException{
    public InvalidDocumentException(String message) {
        super(message);
    }
}
