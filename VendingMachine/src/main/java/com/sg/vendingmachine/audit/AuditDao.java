package com.sg.vendingmachine.audit;

interface AuditDao {
    void writeAuditEntry(String entry) throws AuditPersistenceException;
}
