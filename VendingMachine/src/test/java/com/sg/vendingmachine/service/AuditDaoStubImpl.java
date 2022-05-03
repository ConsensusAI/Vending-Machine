package com.sg.vendingmachine.service;

import com.sg.vendingmachine.audit.AuditDao;
import com.sg.vendingmachine.audit.AuditPersistenceException;

public class AuditDaoStubImpl implements AuditDao {
    @Override
    public void writeAuditEntry(String entry) throws AuditPersistenceException {
        // Do nothing.
    }
}
