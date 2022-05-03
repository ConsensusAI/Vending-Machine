package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendAuditDao;
import com.sg.vendingmachine.dao.AuditPersistenceException;

public class VendAuditDaoStubImpl implements VendAuditDao {
    @Override
    public void writeAuditEntry(String entry) throws AuditPersistenceException {
        // Do nothing.
    }
}
