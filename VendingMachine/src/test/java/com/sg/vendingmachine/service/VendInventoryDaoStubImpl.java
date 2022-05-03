package com.sg.vendingmachine.service;

import com.sg.vendingmachine.inventory.InventoryDao;
import com.sg.vendingmachine.inventory.InventoryPersistenceException;
import com.sg.vendingmachine.inventory.ItemDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendInventoryDaoStubImpl implements InventoryDao {

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
    public List<ItemDto> getAllItems() throws InventoryPersistenceException {
        List<ItemDto> allItems = new ArrayList<>();
        allItems.add(onlyItem);
        return allItems;
    }

    @Override
    public ItemDto getItem(String id) throws InventoryPersistenceException {
        if (id.equals(onlyItem.getId())) {
            return onlyItem;
        }
        return null;
    }

    @Override
    public void updateItem(String id, ItemDto item) throws InventoryPersistenceException {

    }
}
