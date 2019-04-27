package com.test.inputhandlers;

public interface InputHandlerInterface {

    void receiveInput(String input) throws Exception;
    void verifyInputIsValid(String input) throws Exception;

}
