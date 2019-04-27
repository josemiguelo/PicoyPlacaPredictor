package com.test.exceptions;

public class InvalidTimeException extends Exception {

    public InvalidTimeException(String errorMessage) {
        super(errorMessage);
    }

    public InvalidTimeException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}


