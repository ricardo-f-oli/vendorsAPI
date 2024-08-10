package com.example.vendorsAPI.config;

import com.example.vendorsAPI.exceptions.InvalidBirthDayException;
import com.example.vendorsAPI.exceptions.InvalidDocumentException;
import com.example.vendorsAPI.exceptions.InvalidEmailException;
import com.example.vendorsAPI.exceptions.VendorFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidDocumentException.class)
    public ResponseEntity<String> handleInvalidDocumentException(InvalidDocumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<String> handleInvalidEmailException(InvalidEmailException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidBirthDayException.class)
    public ResponseEntity<String> handleBirthDayException(InvalidBirthDayException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(VendorFormatException.class)
    public ResponseEntity<String> handleBirthDayException(VendorFormatException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}