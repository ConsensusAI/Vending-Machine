package com.sg.vendingmachine.inventory;

import java.util.List;

public class InventoryServiceImpl implements InventoryService {
    private final InventoryDao inventoryDao;

    public InventoryServiceImpl(InventoryDao inventoryDao) {
        this.inventoryDao = inventoryDao;
    }

    @Override
    public List<ItemDto> getAllItems() throws InventoryPersistenceException {
        return inventoryDao.getAllItems();
    }

    @Override
    public ItemDto getItem(String id) throws InventoryPersistenceException {
        return inventoryDao.getItem(id);
    }

    @Override
    public void reduceItemStock(String id) throws InventoryPersistenceException {
        ItemDto item = getItem(id);
        item.setStock(item.getStock() - 1);
        inventoryDao.updateItem(id, item);
    }
}
