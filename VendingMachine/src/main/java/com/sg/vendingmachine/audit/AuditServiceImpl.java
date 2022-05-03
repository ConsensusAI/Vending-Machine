package com.sg.vendingmachine.audit;

import com.sg.vendingmachine.transaction.Change;
import com.sg.vendingmachine.inventory.ItemDto;

import java.util.Optional;

public class AuditServiceImpl implements AuditService {
    private final AuditDao auditDao;

    public AuditServiceImpl(AuditDao auditDao) {
        this.auditDao = auditDao;
    }

    @Override
    public void auditTransaction(ItemDto item, Optional<Change> changeMaybe) throws AuditPersistenceException {
        StringBuilder builder = new StringBuilder();
        builder.append("1 " + item.getName() + " sold for "
        + item.getCost() + ". " + item.getStock() +
                " remaining.");
        changeMaybe.ifPresent(change -> builder.append("Change returned: " + change));
        auditDao.writeAuditEntry(builder.toString());
    }
}
