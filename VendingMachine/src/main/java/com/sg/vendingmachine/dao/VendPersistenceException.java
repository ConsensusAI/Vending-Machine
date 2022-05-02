package com.sg.vendingmachine.dao;

public class VendPersistenceException extends Exception {

    public VendPersistenceException(String message)  {
        super(message);
    }

    public VendPersistenceException(String message, Throwable cause)  {
        super(message, cause);
    }
}
