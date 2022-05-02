package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.InventoryPersistenceException;
import com.sg.vendingmachine.dao.VendInventoryDao;
import com.sg.vendingmachine.dto.ItemDto;

import java.util.List;

public class InventoryService {
    private final VendInventoryDao inventoryDao;

    public InventoryService(VendInventoryDao inventoryDao) {
        this.inventoryDao = inventoryDao;
    }

    public List<ItemDto> getAllItems() throws InventoryPersistenceException {
        return inventoryDao.getAllItems();
    }

    public ItemDto getItem(String id) throws InventoryPersistenceException {
        return inventoryDao.getItem(id);
    }

    public void reduceItemStock(String id) throws InventoryPersistenceException {
        ItemDto item = getItem(id);
        item.setStock(item.getStock() - 1);
        inventoryDao.updateItem(id, item);
    }
}
