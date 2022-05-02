package com.sg.vendingmachine.service;

public class NoStockException extends Exception {

    public NoStockException(String message) {
        super(message);
    }

    public NoStockException(String message, Throwable cause) {
        super(message, cause);
    }
}
