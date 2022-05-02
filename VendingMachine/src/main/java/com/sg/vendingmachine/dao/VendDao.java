package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.ItemDto;

import java.math.BigDecimal;
import java.util.List;

public interface VendDao {

    List<ItemDto> getAllItems() throws VendPersistenceException;

    ItemDto getItem(String Id) throws VendPersistenceException;

    int getItemStock(String id) throws VendPersistenceException;

    void reduceItemStock(String id) throws VendPersistenceException;

    BigDecimal getItemCost(String id) throws VendPersistenceException;

    BigDecimal subtractMoney(BigDecimal initMoney, String itemId);

    ItemDto addItem(String id, ItemDto item) throws VendPersistenceException;
}
