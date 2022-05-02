package com.sg.vendingmachine.inventory;

import java.util.List;

public class InventoryService {
    private final InventoryDao inventoryDao;

    public InventoryService(InventoryDao inventoryDao) {
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
