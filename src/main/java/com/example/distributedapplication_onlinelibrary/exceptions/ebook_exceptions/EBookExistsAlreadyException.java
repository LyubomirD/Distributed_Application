package com.example.distributedapplication_onlinelibrary.exceptions.ebook_exceptions;

public class EBookExistsAlreadyException extends IllegalArgumentException {
    public EBookExistsAlreadyException(String s) {
        super(s);
    }
}
