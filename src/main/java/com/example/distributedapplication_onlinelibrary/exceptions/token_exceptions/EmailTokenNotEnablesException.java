package com.example.distributedapplication_onlinelibrary.exceptions.token_exceptions;

import com.example.distributedapplication_onlinelibrary.exceptions.ValidationException;

public class EmailTokenNotEnablesException extends ValidationException {

    public EmailTokenNotEnablesException(String message) {
        super(message);
    }
}
