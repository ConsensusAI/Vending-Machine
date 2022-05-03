package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.ItemDto;

import java.math.BigDecimal;
import java.util.List;

public interface VendInventoryDao {

    List<ItemDto> getAllItems() throws InventoryPersistenceException;

    ItemDto getItem(String id) throws InventoryPersistenceException;

   void updateItem(String id, ItemDto item) throws InventoryPersistenceException;
}
