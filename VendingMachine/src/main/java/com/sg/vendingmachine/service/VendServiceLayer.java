package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendPersistenceException;
import com.sg.vendingmachine.dto.ItemDto;

import java.math.BigDecimal;
import java.util.List;

public interface VendServiceLayer {

    List<ItemDto> getAllItems() throws VendPersistenceException;

//    void checkStock(String itemId) throws VendPersistenceException, VendNoItemInventoryException;
//
//    void compareMoney(BigDecimal userMoney, String itemId) throws VendPersistenceException, VendInsufficientFundsException;
//
//    BigDecimal subtractMoney(BigDecimal moneyInserted, String itemId) throws VendPersistenceException,
//            VendNoItemInventoryException,
//            VendInsufficientFundsException;

//    String returnChange(BigDecimal amount);
}
