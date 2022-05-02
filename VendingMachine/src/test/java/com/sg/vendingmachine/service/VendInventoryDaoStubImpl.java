package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendInventoryDao;
import com.sg.vendingmachine.dao.VendPersistenceException;
import com.sg.vendingmachine.dto.ItemDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendInventoryDaoStubImpl implements VendInventoryDao {

    public ItemDto onlyItem;

    public VendInventoryDaoStubImpl() {
        onlyItem = new ItemDto("1");
        onlyItem.setName("Chips");
        onlyItem.setCost(new BigDecimal("9.99"));
        onlyItem.setStock(10);
    }

    public VendInventoryDaoStubImpl(ItemDto testItem) {
        this.onlyItem = testItem;
    }

    @Override
    public List<ItemDto> getAllItems() throws VendPersistenceException {
        List<ItemDto> allItems = new ArrayList<>();
        allItems.add(onlyItem);
        return allItems;
    }

    @Override
    public ItemDto getItem(String id) throws VendPersistenceException {
        if (id.equals(onlyItem.getId())) {
            return onlyItem;
        }
        return null;
    }

    @Override
    public int getItemStock(String id) throws VendPersistenceException {
        if (id.equals(onlyItem.getId())) {
            return onlyItem.getStock();
        }
        return -1;
    }

    @Override
    public void reduceItemStock(String id) throws VendPersistenceException {
        if (id.equals(onlyItem.getId())) {
            onlyItem.reduceStock();
        }
    }

    @Override
    public BigDecimal getItemCost(String id) throws VendPersistenceException {
        if (id.equals(onlyItem.getId())) {
            return onlyItem.getCost();
        }
        return null;
    }

    @Override
    public BigDecimal subtractMoney(BigDecimal initMoney, String itemId) {
        if (itemId.equals(onlyItem.getId())) {
            BigDecimal cost = onlyItem.getCost();
            return initMoney.subtract(cost);
        }
        return null;
    }

    @Override
    public ItemDto addItem(String id, ItemDto item) throws VendPersistenceException {
        if (id.equals(onlyItem.getId())) {
            return onlyItem;
        }
        return null;
    }
}
