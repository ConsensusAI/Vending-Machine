package com.sg.vendingmachine.audit;

public class AuditPersistenceException extends Exception {

    public AuditPersistenceException(String message)  {
        super(message);
    }

    public AuditPersistenceException(String message, Throwable cause)  {
        super(message, cause);
    }
}
