package com.sg.vendingmachine.service;

public class VendNoItemInventoryException extends Exception {

    public VendNoItemInventoryException(String message) {
        super(message);
    }

    public VendNoItemInventoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
