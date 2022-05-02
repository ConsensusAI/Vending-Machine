package com.sg.vendingmachine.service;

public class VendInsufficientFundsException extends Exception {

    public VendInsufficientFundsException(String message) {
        super(message);
    }

    public VendInsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}
