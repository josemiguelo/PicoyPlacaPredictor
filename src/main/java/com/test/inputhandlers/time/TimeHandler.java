package com.test.inputhandlers.time;

import com.test.exceptions.InvalidTimeException;
import com.test.inputhandlers.InputHandlerInterface;

import java.time.LocalTime;

public class TimeHandler implements InputHandlerInterface {

    private String time;

    @Override
    public void receiveInput(String timeInput) throws Exception {
        this.verifyInputIsValid(timeInput);
        // this.setTime((timeInput.length()<=5) ? timeInput.concat(":00") : timeInput);
        this.setTime(timeInput);
    }

    @Override
    public void verifyInputIsValid(String time) throws Exception {

        if (time == null) {
            throw new InvalidTimeException("Time cannot be null");
        }

        if ("".equals(time)) {
            throw new InvalidTimeException("Time cannot be empty");
        }

        try {
            LocalTime.parse(time);
        } catch (Exception e) {
            throw new InvalidTimeException("Time is invalid", e);
        }
    }

    public LocalTime getLocalTime () throws Exception {
        if (this.getTime() == null) {
            throw new InvalidTimeException("Time is not set");
        }
        try {
            return LocalTime.parse(time);
        } catch (Exception e) {
            throw new InvalidTimeException("Time is invalid", e);
        }
    }

    public String getTime() {
        // return (this.time.length()<=5) ? this.time.concat(":00") : this.time;
        return this.time;
    }

    private void setTime(String time) {
        this.time = time;
    }

}
