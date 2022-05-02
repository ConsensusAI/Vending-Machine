package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.ItemDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VendDaoFileImplTest {

    VendDao testDao;

    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testInventory.txt";
        new FileWriter(testFile);
        testDao = new VendDaoFileImpl(testFile);
    }

    @Test
    public void testGetItem() throws Exception {
        // Set up test inputs
        String id = "1";
        ItemDto item = new ItemDto(id);
        item.setName("Chips");
        item.setCost(new BigDecimal("9.99"));
        item.setStock(10);

        testDao.addItem(id, item);
        ItemDto retrievedItem = testDao.getItem(id);

        assertEquals(item.getId(),
                retrievedItem.getId(),
                "Checking item id.");
        assertEquals(item.getName(),
                retrievedItem.getName(),
                "Checking item name.");
        assertEquals(item.getCost(),
                retrievedItem.getCost(),
                "Checking item cost.");
        assertEquals(item.getStock(),
                retrievedItem.getStock(),
                "Checking item cost.");
    }



}