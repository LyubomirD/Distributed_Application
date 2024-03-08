package com.example.distributedapplication_onlinelibrary.exceptions.ebook_exception;

public class EBookNotFoundException extends IllegalArgumentException {

    public EBookNotFoundException(String message) {
        super(message);
    }
}
