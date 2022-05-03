package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendAuditDao;
import com.sg.vendingmachine.dao.VendInventoryDao;
import com.sg.vendingmachine.dao.AuditPersistenceException;

import java.math.BigDecimal;

public class TransactionService {
    private final VendInventoryDao inventoryDao;
    private final VendAuditDao auditDao;
    private final ChangeService changeService;

    public TransactionService(VendInventoryDao inventoryDao, VendAuditDao auditDao) {
        this.inventoryDao = inventoryDao;
        this.auditDao = auditDao;
        this.changeService = new ChangeService();
    }

    public void checkStock(String itemId) throws AuditPersistenceException, NoStockException {
        if (inventoryDao.getItem(itemId).getStock() <= 0) {
            throw new NoStockException("ERROR: Item is out of stock.");
        }
    }

    public void compareMoney(BigDecimal userMoney, String itemId) throws AuditPersistenceException, InsufficientFundsException {
        if (userMoney.compareTo(inventoryDao.getItemCost(itemId)) < 0) {
            throw new InsufficientFundsException("ERROR: Insufficient funds.");
        }
    }

    public BigDecimal subtractMoney(BigDecimal moneyInserted, String itemId) throws AuditPersistenceException,
            NoStockException,
            InsufficientFundsException {
        compareMoney(moneyInserted, itemId);
        checkStock(itemId);
        moneyInserted = inventoryDao.subtractMoney(moneyInserted, itemId);
        inventoryDao.reduceItemStock(itemId);

        auditDao.writeAuditEntry("1 " + inventoryDao.getItem(itemId).getName() + " sold for "
                + inventoryDao.getItemCost(itemId) + ". " + inventoryDao.getItemStock(itemId) +
                " remaining. Change returned: $" + moneyInserted);
        return moneyInserted;
    }

    public String getChange(BigDecimal amount) {
        return changeService.returnChange(amount);
    }
}
