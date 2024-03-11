package com.example.distributedapplication_onlinelibrary.exceptions.user_role_access_exceptions;

public class AdminAccessDeniedException extends RuntimeException {
    public AdminAccessDeniedException(String message) {
        super(message);
    }
}

