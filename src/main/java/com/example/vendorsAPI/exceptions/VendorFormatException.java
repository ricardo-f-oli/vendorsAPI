package com.example.vendorsAPI.exceptions;

public class VendorFormatException extends IllegalArgumentException{
    public VendorFormatException(String message) {
        super(message);
    }
}
