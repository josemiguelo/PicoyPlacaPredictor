package com.test.inputhandlers.date;

import com.test.exceptions.InvalidDateException;
import com.test.inputhandlers.InputHandlerInterface;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateHandler implements InputHandlerInterface {

    private String date;

    @Override
    public void receiveInput(String dateInput) throws Exception {
        this.verifyInputIsValid(dateInput);
        this.setDate(dateInput);
    }

    @Override
    public void verifyInputIsValid(String dateString) throws Exception {

        if (dateString == null) {
            throw new InvalidDateException("Date cannot be null");
        }

        if ("".equals(dateString)) {
            throw new InvalidDateException("Date cannot be empty");
        }

        try {
            LocalDate.parse(dateString);
        } catch (Exception e) {
            throw new InvalidDateException("Date is invalid", e);
        }

        if (new SimpleDateFormat("yyyy-MM-dd").parse(dateString).before(new Date())) {
            throw new InvalidDateException("Date has past");
        }

    }


    public LocalDate getLocalDate() throws  Exception {
        if (this.getDate() == null) {
            throw new InvalidDateException("Date is not set");
        }
        try {
            return LocalDate.parse(this.getDate());
        } catch (Exception e) {
            throw new InvalidDateException("Date is invalid", e);
        }
    }

    public String getDate() {
        return date;
    }

    private void setDate(String date) {
        this.date = date;
    }

}
