package com.sg.vendingmachine.service;

import com.sg.vendingmachine.Change;
import com.sg.vendingmachine.dao.AuditPersistenceException;
import com.sg.vendingmachine.dao.InventoryPersistenceException;
import com.sg.vendingmachine.dto.ItemDto;

import java.math.BigDecimal;
import java.util.Optional;

public class TransactionService {
    private final InventoryService inventoryService;
    private final AuditService auditService;
    private final ChangeService changeService;

    public TransactionService(InventoryService inventoryService, AuditService auditService, ChangeService changeService) {
        this.inventoryService = inventoryService;
        this.auditService = auditService;
        this.changeService = changeService;
    }

    public Optional<Change> computeTransaction(BigDecimal moneyInserted, String itemId)
            throws InventoryPersistenceException, AuditPersistenceException, NoStockException, InsufficientFundsException {
        ItemDto item = inventoryService.getItem(itemId);

        checkStock(item);
        compareMoney(moneyInserted, item);

        inventoryService.reduceItemStock(itemId);

        Optional<Change> changeMaybe = Optional.empty();
        BigDecimal amountToReturn = moneyInserted.subtract(item.getCost());
        if (amountToReturn.compareTo(BigDecimal.ZERO) > 0) { // Checks if amount to return is greater than 0.00
            changeMaybe = Optional.of(changeService.computeChange(amountToReturn));
        }

        auditService.auditTransaction(item, changeMaybe);
        return changeMaybe;
    }

    private void checkStock(ItemDto item) throws NoStockException {
        if (item.getStock() <= 0) {
            throw new NoStockException("ERROR: Item is out of stock.");
        }
    }

    private void compareMoney(BigDecimal moneyInserted, ItemDto item) throws InsufficientFundsException {
        if (moneyInserted.compareTo(item.getCost()) < 0) {
            throw new InsufficientFundsException("ERROR: Insufficient funds.");
        }
    }
}
