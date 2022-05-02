package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendAuditDao;
import com.sg.vendingmachine.dao.VendInventoryDao;
import com.sg.vendingmachine.dao.VendPersistenceException;

import java.math.BigDecimal;

public class TransactionService {
    private VendInventoryDao dao;
    private VendAuditDao auditDao;
    private ChangeService changeService;

    public TransactionService(VendInventoryDao dao, VendAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
        this.changeService = new ChangeService();
    }

    public void checkStock(String itemId) throws VendPersistenceException, NoStockException {
        if (dao.getItem(itemId).getStock() <= 0) {
            throw new NoStockException("ERROR: Item is out of stock.");
        }
    }

    public void compareMoney(BigDecimal userMoney, String itemId) throws VendPersistenceException, InsufficientFundsException {
        if (userMoney.compareTo(dao.getItemCost(itemId)) < 0) {
            throw new InsufficientFundsException("ERROR: Insufficient funds.");
        }
    }

    public BigDecimal subtractMoney(BigDecimal moneyInserted, String itemId) throws VendPersistenceException,
            NoStockException,
            InsufficientFundsException {
        compareMoney(moneyInserted, itemId);
        checkStock(itemId);
        moneyInserted = dao.subtractMoney(moneyInserted, itemId);
        dao.reduceItemStock(itemId);

        auditDao.writeAuditEntry("1 " + dao.getItem(itemId).getName() + " sold for "
                + dao.getItemCost(itemId) + ". " + dao.getItemStock(itemId) +
                " remaining. Change returned: $" + moneyInserted);
        return moneyInserted;
    }

    public String getChange(BigDecimal amount) {
        return changeService.returnChange(amount);
    }
}
