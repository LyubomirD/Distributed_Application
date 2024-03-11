package com.example.distributedapplication_onlinelibrary.exceptions.author_exceptions;

public class AuthorExistsAlreadyException extends IllegalArgumentException {
    public AuthorExistsAlreadyException(String s) {
        super(s);
    }
}
