package com.sg.vendingmachine.audit;

import com.sg.vendingmachine.inventory.ItemDto;
import com.sg.vendingmachine.transaction.Change;

import java.util.Optional;

public class AuditService {
    private final AuditDao auditDao;

    public AuditService(AuditDao auditDao) {
        this.auditDao = auditDao;
    }

    public void auditTransaction(ItemDto item, Optional<Change> changeMaybe) throws AuditPersistenceException {
        StringBuilder builder = new StringBuilder();
        builder.append(("1 " + item.getName() + " sold for "
                + item.getCost() + ". " + item.getStock() +
                " remaining."));
        changeMaybe.ifPresent(change -> builder.append("Change returned: " + change));
        auditDao.writeAuditEntry(builder.toString());
    }
}
