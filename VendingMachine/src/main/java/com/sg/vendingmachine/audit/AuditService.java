package com.sg.vendingmachine.audit;

import com.sg.vendingmachine.inventory.ItemDto;
import com.sg.vendingmachine.transaction.Change;

import java.util.Optional;

public interface AuditService {

    public void auditTransaction(ItemDto item, Optional<Change> changeMaybe) throws AuditPersistenceException;
}
