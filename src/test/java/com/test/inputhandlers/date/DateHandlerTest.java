package com.test.inputhandlers.date;

import com.test.exceptions.InvalidDateException;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class DateHandlerTest {
    private DateHandler handler;

    @Before
    public final void init() {
        this.handler = new DateHandler();
    }

    @Test(expected = InvalidDateException.class)
    public void testReceiveNullDate() throws Exception {
        this.handler.verifyInputIsValid(null);
    }

    @Test(expected = InvalidDateException.class)
    public void testReceiveEmptyDate() throws Exception {
        this.handler.verifyInputIsValid("");
    }

    @Test(expected = InvalidDateException.class)
    public void testReceiveInvalidFormatDate() throws Exception {
        String dateInput = "01012000";
        this.handler.verifyInputIsValid(dateInput);
    }

    @Test
    public void testReceiveValidDate() throws Exception {
        String validDate = "2020-01-01";
        this.handler.receiveInput(validDate);
        assertNotNull(this.handler.getDate());
        assertEquals(this.handler.getDate(), validDate);
    }

    @Test
    public void testGetLocalDate() throws Exception {
        String validDate = "2019-05-21";
        this.handler.receiveInput(validDate);
        assertEquals(LocalDate.of(2019,5,21), this.handler.getLocalDate());
    }

    @Test(expected =  InvalidDateException.class)
    public void testReceivedDateIsPast() throws Exception {
        String dateInput = "2019-04-26";
        this.handler.verifyInputIsValid(dateInput);
    }
}
