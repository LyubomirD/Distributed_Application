package com.example.distributedapplication_onlinelibrary.exceptions.password_exceptions;

import com.example.distributedapplication_onlinelibrary.exceptions.ValidationException;

public class PasswordValidationException extends ValidationException {
    public PasswordValidationException(String message) {
        super(message);
    }
}
