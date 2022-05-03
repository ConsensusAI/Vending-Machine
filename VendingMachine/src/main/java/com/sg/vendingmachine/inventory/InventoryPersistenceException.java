package com.sg.vendingmachine.inventory;

public class InventoryPersistenceException extends Exception {

    public InventoryPersistenceException(String message) {
        super(message);
    }

    public InventoryPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}