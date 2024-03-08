package com.example.distributedapplication_onlinelibrary.exceptions.token_exceptions;

import com.example.distributedapplication_onlinelibrary.exceptions.ValidationException;

public class EmailTokenExpiredException extends ValidationException {
    public EmailTokenExpiredException(String message) {
        super(message);
    }
}
