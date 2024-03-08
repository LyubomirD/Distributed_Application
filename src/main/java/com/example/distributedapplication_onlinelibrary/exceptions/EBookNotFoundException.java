package com.example.distributedapplication_onlinelibrary.exceptions;

public class EBookNotFoundException extends IllegalArgumentException {

    public EBookNotFoundException(String message) {
        super(message);
    }
}
