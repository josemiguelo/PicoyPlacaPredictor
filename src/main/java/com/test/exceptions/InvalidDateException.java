package com.test.exceptions;

public class InvalidDateException extends Exception {

    public InvalidDateException(String errorMessage) {
        super(errorMessage);
    }

    public InvalidDateException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}


