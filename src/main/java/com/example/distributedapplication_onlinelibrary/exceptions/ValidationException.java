package com.example.distributedapplication_onlinelibrary.exceptions;

import org.hibernate.exception.ConstraintViolationException;

import java.sql.SQLException;

public class ValidationException extends ConstraintViolationException {

    public ValidationException(String message) {
        super(message, new SQLException(message), null);
    }
}
