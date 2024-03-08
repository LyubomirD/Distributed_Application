package com.example.distributedapplication_onlinelibrary.exceptions;

public class EmailTokenExpiredException extends ValidationException{
    public EmailTokenExpiredException(String message) {
        super(message);
    }
}
