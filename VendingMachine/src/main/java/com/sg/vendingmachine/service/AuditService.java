package com.sg.vendingmachine.service;

import com.sg.vendingmachine.Change;
import com.sg.vendingmachine.dao.VendAuditDao;
import com.sg.vendingmachine.dto.ItemDto;

import java.util.Optional;

public class AuditService {
    private final VendAuditDao auditDao;

    public AuditService(VendAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    public void auditTransaction(ItemDto item, Optional<Change> changeMaybe) throws AuditPersistenceException {
        StringBuilder builder = new StringBuilder();
        builder.append("1 " + item.getName() + " sold for "
        + item.getCost() + ". " + item.getStock() +
                " remaining.");
        changeMaybe.ifPresent(change -> builder.append("Change returned: " + change));
        auditDao.writeAuditEntry(builder.toString());
    }
}
