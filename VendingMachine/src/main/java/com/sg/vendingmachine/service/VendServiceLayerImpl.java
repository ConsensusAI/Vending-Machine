package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendDao;
import com.sg.vendingmachine.dao.VendPersistenceException;
import com.sg.vendingmachine.dto.ItemDto;

import java.math.BigDecimal;
import java.util.List;

public class VendServiceLayerImpl implements VendServiceLayer {
    VendDao dao;
//    private VendAuditDao auditDao;

    public VendServiceLayerImpl(VendDao dao) {
        this.dao = dao;
    }

    @Override
    public List<ItemDto> getAllItems() throws VendPersistenceException {
        return dao.getAllItems();
    }

    @Override
    public void checkStock(String itemId) throws VendPersistenceException, VendNoItemInventoryException {
        if (dao.getItem(itemId).getStock() < 0) {
            throw new VendNoItemInventoryException("ERROR: Item is out of stock.");
        }
    }

    @Override
    public void compareMoney(BigDecimal userMoney, String itemId) throws VendPersistenceException, VendInsufficientFundsException {
        if (userMoney.compareTo(dao.getItemCost(itemId)) >= 0) {
            throw new VendInsufficientFundsException("ERROR: Insufficient funds.");
        }
    }

    @Override
    public BigDecimal subtractMoney(BigDecimal moneyInserted, String itemId) throws VendPersistenceException,
            VendNoItemInventoryException,
            VendInsufficientFundsException{
        compareMoney(moneyInserted, itemId);
        moneyInserted = dao.subtractMoney(moneyInserted, itemId);
        checkStock(itemId);
        dao.reduceItemStock(itemId);
        return moneyInserted;
    }
}
