package com.sg.vendingmachine.dao;

public class InventoryPersistenceException extends Exception {

    public InventoryPersistenceException(String message) {
        super(message);
    }

    public InventoryPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
