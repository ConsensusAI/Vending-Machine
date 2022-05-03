package com.sg.vendingmachine.inventory;

import java.util.List;

public interface InventoryService {
    List<ItemDto> getAllItems() throws InventoryPersistenceException;

    ItemDto getItem(String id) throws InventoryPersistenceException;

    void reduceItemStock(String id) throws InventoryPersistenceException;
}
