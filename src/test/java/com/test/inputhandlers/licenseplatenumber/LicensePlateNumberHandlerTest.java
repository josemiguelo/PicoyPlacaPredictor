package com.test.inputhandlers.licenseplatenumber;

import com.test.exceptions.InvalidLicensePlateNumberException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LicensePlateNumberHandlerTest {

    private LicensePlateNumberHandler handler;

    @Before
    public final void init() {
        this.handler = new LicensePlateNumberHandler();
    }

    @Test(expected = InvalidLicensePlateNumberException.class)
    public void testVerifyNullLicensePlateNumber() throws Exception {
        this.handler.verifyInputIsValid(null);
        assertNull(this.handler.getLicensePlateNumber());
    }

    @Test(expected = InvalidLicensePlateNumberException.class)
    public void testVerifyEmptyLicensePlateNumber() throws Exception {
        this.handler.verifyInputIsValid("");
        assertNull(this.handler.getLicensePlateNumber());
    }

    @Test(expected = InvalidLicensePlateNumberException.class)
    public void testVerifyLicensePlateNumberWithInvalidCharacters() throws Exception {
        String licensePlateNumberInput = "ABC_9867";
        this.handler.verifyInputIsValid(licensePlateNumberInput);
        assertNull(this.handler.getLicensePlateNumber());
    }

    @Test(expected = InvalidLicensePlateNumberException.class)
    public void testVerifyInvalidLicensePlateNumber() throws Exception {
        String licensePlateNumberInput = "ASD098";
        this.handler.verifyInputIsValid(licensePlateNumberInput);
        assertNull(this.handler.getLicensePlateNumber());
    }

    @Test
    public void testReceiveValidLicensePlateNumberInput() throws Exception {
        String validLicensePlateNumber = "LKJ-4568";
        this.handler.receiveInput(validLicensePlateNumber);
        assertNotNull(this.handler.getLicensePlateNumber());
        assertEquals(this.handler.getLicensePlateNumber(), validLicensePlateNumber);
    }

    @Test
    public void testGetLastDigitOfLicensePlateNumber() {
        String licensePlateNumber = "GHJ-5678";
        assertEquals("8", handler.getLastDigit(licensePlateNumber));

        String licensePlateNumber2 = "IP-626V";
        assertEquals("6", handler.getLastDigit(licensePlateNumber2));
    }
}
