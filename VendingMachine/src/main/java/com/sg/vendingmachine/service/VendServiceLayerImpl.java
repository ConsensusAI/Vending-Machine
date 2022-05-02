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
    public boolean checkStock(String itemId) throws VendPersistenceException {
        return false;
    }

    @Override
    public boolean compareMoney(BigDecimal userMoney, String itemId) throws VendPersistenceException {
        return userMoney.compareTo(dao.getItemCost(itemId)) >= 0;
    }

    @Override
    public BigDecimal subtractMoney(BigDecimal moneyInserted, String itemId) throws VendPersistenceException {
        moneyInserted = dao.subtractMoney(moneyInserted, itemId);
        dao.reduceItemStock(itemId);
        return moneyInserted;
    }
}
