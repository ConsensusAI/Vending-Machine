package com.sg.vendingmachine.service;

import com.sg.vendingmachine.Change;
import com.sg.vendingmachine.dao.VendAuditDao;
import com.sg.vendingmachine.dao.VendDao;
import com.sg.vendingmachine.dao.VendPersistenceException;
import com.sg.vendingmachine.dto.ItemDto;

import java.math.BigDecimal;
import java.util.List;

public class VendServiceLayerImpl implements VendServiceLayer {
    VendDao dao;
    private final VendAuditDao auditDao;

    public VendServiceLayerImpl(VendDao dao, VendAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public List<ItemDto> getAllItems() throws VendPersistenceException {
        return dao.getAllItems();
    }

    @Override
    public void checkStock(String itemId) throws VendPersistenceException, VendNoItemInventoryException {
        if (dao.getItem(itemId).getStock() <= 0) {
            throw new VendNoItemInventoryException("ERROR: Item is out of stock.");
        }
    }

    @Override
    public void compareMoney(BigDecimal userMoney, String itemId) throws VendPersistenceException, VendInsufficientFundsException {
        if (userMoney.compareTo(dao.getItemCost(itemId)) < 0) {
            throw new VendInsufficientFundsException("ERROR: Insufficient funds.");
        }
    }

    @Override
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

    public String returnChange(BigDecimal amount) {
        Change change = new Change(amount);
        int quarters = change.returnQuarters();
        int dimes = change.returnDimes();
        int nickels = change.returnNickels();
        int pennies = change.returnPennies();
        String moneyReturned = "Your change: ";
        if (quarters > 0) {
            moneyReturned += "\n" + quarters + " Quarters";
        }
        if (dimes > 0) {
            moneyReturned += "\n" + dimes + " Dimes";
        }
        if (nickels > 0) {
            moneyReturned += "\n" + nickels + " Nickels";
        }
        moneyReturned += "\n" + pennies + " Pennies";
        return moneyReturned;
    }
}
