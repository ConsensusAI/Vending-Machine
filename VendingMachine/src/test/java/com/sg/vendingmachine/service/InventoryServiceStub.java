package com.sg.vendingmachine.service;

import com.sg.vendingmachine.inventory.InventoryDao;
import com.sg.vendingmachine.inventory.InventoryPersistenceException;
import com.sg.vendingmachine.inventory.InventoryService;
import com.sg.vendingmachine.inventory.ItemDto;

import java.util.List;

public class InventoryServiceStub implements InventoryService {
    private final InventoryDao inventoryDao;

    public InventoryServiceStub() {
        this.inventoryDao = new InventoryDaoStubImpl();
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
