package com.sg.vendingmachine.audit;

public interface AuditDao {

    void writeAuditEntry(String entry) throws AuditPersistenceException;
}
