package com.example.distributedapplication_onlinelibrary.exceptions;

public class BookNotFoundException extends IllegalArgumentException {

    public BookNotFoundException(String message) {
        super(message);
    }
}
