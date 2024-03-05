package com.example.distributedapplication_onlinelibrary.exceptions;

public class EmailTakenException extends ValidationException {

    public EmailTakenException(String message) {
        super(message);
    }
}
