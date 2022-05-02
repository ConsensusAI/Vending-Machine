package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.ItemDto;

import java.math.BigDecimal;
import java.util.Map;

public interface VendDao {

    Map<String, ItemDto> getAllItems();

    ItemDto getItem(String Id);

    int getItemStock(String name);

    void reduceItemStock(String name);

    BigDecimal getItemCost(String id);

    BigDecimal subtractMoney(BigDecimal initMoney, String itemId);
}
