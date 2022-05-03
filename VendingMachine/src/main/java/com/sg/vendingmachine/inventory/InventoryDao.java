package com.sg.vendingmachine.inventory;

import java.util.List;

public interface InventoryDao {

    List<ItemDto> getAllItems() throws InventoryPersistenceException;

    ItemDto getItem(String id) throws InventoryPersistenceException;

   void updateItem(String id, ItemDto item) throws InventoryPersistenceException;
}
