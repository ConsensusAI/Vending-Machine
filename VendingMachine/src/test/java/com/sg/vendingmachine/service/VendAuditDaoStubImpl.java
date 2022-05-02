package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendAuditDao;
import com.sg.vendingmachine.dao.VendPersistenceException;

public class VendAuditDaoStubImpl implements VendAuditDao {
    @Override
    public void writeAuditEntry(String entry) throws VendPersistenceException {
        // Do nothing.
    }
}
