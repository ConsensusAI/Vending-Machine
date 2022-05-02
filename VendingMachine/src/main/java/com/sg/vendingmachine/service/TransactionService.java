package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendAuditDao;
import com.sg.vendingmachine.dao.VendDao;
import com.sg.vendingmachine.dao.VendPersistenceException;

import java.math.BigDecimal;

public class TransactionService {
    private VendDao dao;
    private VendAuditDao auditDao;
    private ChangeService changeService;

    public TransactionService(VendDao dao, VendAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
        this.changeService = new ChangeService();
    }

    public void checkStock(String itemId) throws VendPersistenceException, VendNoItemInventoryException {
        if (dao.getItem(itemId).getStock() <= 0) {
            throw new VendNoItemInventoryException("ERROR: Item is out of stock.");
        }
    }

    public void compareMoney(BigDecimal userMoney, String itemId) throws VendPersistenceException, VendInsufficientFundsException {
        if (userMoney.compareTo(dao.getItemCost(itemId)) < 0) {
            throw new VendInsufficientFundsException("ERROR: Insufficient funds.");
        }
    }

    public BigDecimal subtractMoney(BigDecimal moneyInserted, String itemId) throws VendPersistenceException,
            VendNoItemInventoryException,
            VendInsufficientFundsException{
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
