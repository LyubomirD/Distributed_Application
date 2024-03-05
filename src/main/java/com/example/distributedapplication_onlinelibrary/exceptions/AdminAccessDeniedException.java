package com.example.distributedapplication_onlinelibrary.exceptions;

public class AdminAccessDeniedException extends RuntimeException {
    public AdminAccessDeniedException(String message) {
        super(message);
    }
}

