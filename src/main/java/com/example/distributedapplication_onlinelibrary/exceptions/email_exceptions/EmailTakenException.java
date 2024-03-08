package com.example.distributedapplication_onlinelibrary.exceptions.email_exceptions;

import com.example.distributedapplication_onlinelibrary.exceptions.ValidationException;

public class EmailTakenException extends ValidationException {

    public EmailTakenException(String message) {
        super(message);
    }
}
