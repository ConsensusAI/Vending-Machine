package com.sg.vendingmachine.dao;

public interface VendAuditDao {

    public void writeAuditEntry(String entry) throws VendPersistenceException;
}
