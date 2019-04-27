package com.test.exceptions;

public class InvalidLicensePlateNumberException extends Exception {

    public InvalidLicensePlateNumberException(String errorMessage) {
        super(errorMessage);
    }

    public InvalidLicensePlateNumberException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}


