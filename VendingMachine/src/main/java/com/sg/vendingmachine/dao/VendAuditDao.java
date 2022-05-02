package com.sg.vendingmachine.dao;

public interface VendAuditDao {

    void writeAuditEntry(String entry) throws VendPersistenceException;
}
