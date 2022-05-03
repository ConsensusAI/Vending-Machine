package com.sg.vendingmachine.transaction;

import com.sg.vendingmachine.audit.AuditService;
import com.sg.vendingmachine.inventory.InventoryPersistenceException;
import com.sg.vendingmachine.audit.AuditPersistenceException;
import com.sg.vendingmachine.inventory.ItemDto;
import com.sg.vendingmachine.inventory.InventoryService;

import java.math.BigDecimal;
import java.util.Optional;

public class TransactionService {
    private final InventoryService inventoryService;
    private final AuditService auditService;
    private final ChangeCalculator changeCalculator;

    public TransactionService(InventoryService inventoryService, AuditService auditService, ChangeCalculator changeCalculator) {
        this.inventoryService = inventoryService;
        this.auditService = auditService;
        this.changeCalculator = changeCalculator;
    }

    public void checkStock(ItemDto item) throws NoStockException {
        if (item.getStock() <= 0) {
            throw new NoStockException("ERROR: Item is out of stock.");
        }
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
            changeMaybe = Optional.of(changeCalculator.computeChange(amountToReturn));
        }

        auditService.auditTransaction(item, changeMaybe);
        return changeMaybe;
    }

    public void compareMoney(BigDecimal moneyInserted, ItemDto item) throws AuditPersistenceException, InsufficientFundsException {
        if (moneyInserted.compareTo(item.getCost()) < 0) {
            throw new InsufficientFundsException("ERROR: Insufficient funds.");
        }
    }
}
