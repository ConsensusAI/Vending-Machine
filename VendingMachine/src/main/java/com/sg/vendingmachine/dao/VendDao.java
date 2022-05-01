package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.ItemDto;

import java.util.List;

public interface VendDao {

    List<ItemDto> getAllItems();

    ItemDto getItem(String name);

    int getItemStock(String name);

    void reduceItemStock(String name);
}
