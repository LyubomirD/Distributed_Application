package com.example.distributedapplication_onlinelibrary.exceptions.author_exceptions;

public class AuthorNotFoundException extends IllegalArgumentException {
    public AuthorNotFoundException(String s) {
        super(s);
    }
}
