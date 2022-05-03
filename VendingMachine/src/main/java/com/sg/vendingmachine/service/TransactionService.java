package com.sg.vendingmachine.service;

import com.sg.vendingmachine.Change;
import com.sg.vendingmachine.dao.InventoryPersistenceException;
import com.sg.vendingmachine.dao.VendAuditDao;
import com.sg.vendingmachine.dao.VendInventoryDao;
import com.sg.vendingmachine.dao.AuditPersistenceException;
import com.sg.vendingmachine.dto.ItemDto;

import java.math.BigDecimal;
import java.util.Optional;

public class TransactionService {
    private final InventoryService inventoryService;
    private final AuditService auditService;
    private final ChangeService changeService;

    public TransactionService(InventoryService inventoryService, AuditService auditService) {
        this.inventoryService = inventoryService;
        this.auditService = auditService;
        this.changeService = new ChangeService();
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
            changeMaybe = Optional.of(changeService.computeChange(amountToReturn));
        }

        auditService.auditTransaction(item, changeMaybe);
        return changeMaybe;
    }

    public void compareMoney(BigDecimal moneyInserted, ItemDto item) throws AuditPersistenceException, InsufficientFundsException {
        if (moneyInserted.compareTo(item.getCost()) < 0) {
            throw new InsufficientFundsException("ERROR: Insufficient funds.");
        }
    }

    public BigDecimal subtractMoney(BigDecimal moneyInserted, String itemId) throws AuditPersistenceException,
            NoStockException,
            InsufficientFundsException {
        compareMoney(moneyInserted, itemId);
        checkStock(itemId);
        moneyInserted = inventoryService.subtractMoney(moneyInserted, itemId);
        inventoryService.reduceItemStock(itemId);

        auditService.writeAuditEntry("1 " + inventoryService.getItem(itemId).getName() + " sold for "
                + inventoryService.getItemCost(itemId) + ". " + inventoryService.getItemStock(itemId) +
                " remaining. Change returned: $" + moneyInserted);
        return moneyInserted;
    }

    public String getChange(BigDecimal amount) {
        return changeService.returnChange(amount);
    }
}
