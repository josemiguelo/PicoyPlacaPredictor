package com.test.inputhandlers.time;

import com.test.exceptions.InvalidTimeException;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.*;

public class TimeHandlerTest {

    private TimeHandler handler;

    @Before
    public final void init() {
        this.handler = new TimeHandler();
    }

    @Test(expected = InvalidTimeException.class)
    public void testReceiveNullTime() throws Exception {
        this.handler.verifyInputIsValid(null);
        assertNull(this.handler.getTime());
    }

    @Test(expected = InvalidTimeException.class)
    public void testReceiveEmptyTime() throws Exception {
        this.handler.verifyInputIsValid("");
        assertNull(this.handler.getTime());
    }

    @Test(expected = InvalidTimeException.class)
    public void testReceiveInvalidTime() throws Exception {
        String dateInput = "00-00";
        this.handler.verifyInputIsValid(dateInput);
        assertNull(this.handler.getTime());
    }

    @Test
    public void testReceiveValidTime() throws Exception {
        String validTime = "15:34";
        this.handler.receiveInput(validTime);
        assertNotNull(this.handler.getTime());
        assertEquals(this.handler.getTime(), validTime);
    }

    @Test
    public void testGetLocalTimeFromString() throws Exception {
        String validTime = "00:00";
        this.handler.receiveInput(validTime);
        assertEquals(LocalTime.of(0, 0), this.handler.getLocalTime());
    }


}
