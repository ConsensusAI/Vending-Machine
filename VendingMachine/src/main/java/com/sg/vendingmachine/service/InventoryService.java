package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendInventoryDao;
import com.sg.vendingmachine.dao.VendPersistenceException;
import com.sg.vendingmachine.dto.ItemDto;

import java.util.List;

public class InventoryService {
    private final VendInventoryDao inventoryDao;

    public InventoryService(VendInventoryDao inventoryDao) {
        this.inventoryDao = inventoryDao;
    }

    public List<ItemDto> getAllItems() throws VendPersistenceException {
        return inventoryDao.getAllItems();
    }
}