package com.test.inputhandlers.licenseplatenumber;

import com.test.exceptions.InvalidLicensePlateNumberException;
import com.test.inputhandlers.InputHandlerInterface;

public class LicensePlateNumberHandler implements InputHandlerInterface {

    private String licensePlateNumber;

    @Override
    public void receiveInput(String licensePlateNumberInput) throws Exception {
        this.verifyInputIsValid(licensePlateNumberInput);
        this.setLicensePlateNumber(licensePlateNumberInput.toUpperCase());
    }

    @Override
    public void verifyInputIsValid(String licensePlateNumberInput) throws Exception {

        if (licensePlateNumberInput == null) {
            throw new InvalidLicensePlateNumberException("License Plate Number cannot be null");
        }

        if ("".equals(licensePlateNumberInput)) {
            throw new InvalidLicensePlateNumberException("License Plate Number cannot be empty");
        }

        boolean inputMatchesCarLicensePlateNumber = licensePlateNumberInput.matches("[a-zA-Z]+-[0-9]+");
        boolean inputMatchesMotorcycleLicensePlateNumber = licensePlateNumberInput.matches("[a-zA-Z]+-[0-9a-zA-Z]+");
        if (!(inputMatchesCarLicensePlateNumber || inputMatchesMotorcycleLicensePlateNumber)) {
            throw new InvalidLicensePlateNumberException("License Plate Number is invalid");
        }

    }

    public String getLastDigit(String licensePlateNumber) {
        String lastDigit = null;
        for (int i = 0; i < licensePlateNumber.length(); i++) {
            if(Character.isDigit(licensePlateNumber.charAt(i))) {
                lastDigit = String.valueOf(licensePlateNumber.charAt(i));
            }
        }
        return lastDigit;
    }


    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    private void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

}
