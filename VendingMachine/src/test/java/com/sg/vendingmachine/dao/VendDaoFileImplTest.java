package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.ItemDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void testGetAllItems() throws Exception {
        ItemDto firstItem = new ItemDto("1");
        firstItem.setName("Chips");
        firstItem.setCost(new BigDecimal("9.99"));
        firstItem.setStock(10);

        ItemDto secondItem = new ItemDto("2");
        secondItem.setName("Granola Bar");
        secondItem.setCost(new BigDecimal("1.03"));
        secondItem.setStock(5);

        testDao.addItem(firstItem.getId(), firstItem);
        testDao.addItem(secondItem.getId(), secondItem);

        List<ItemDto> allItems = testDao.getAllItems();

        assertNotNull(allItems, "The list of items must not be null.");
        assertEquals(2, allItems.size(), "The list of items should have 2 items.");

        assertTrue(testDao.getAllItems().contains(firstItem), "The list of items should include Chips.");
        assertTrue(testDao.getAllItems().contains(secondItem), "The list of items should include Granola Bar.");
    }

    @Test
    public void testGetReduceItemStock() throws Exception {}

    @Test
    public void testGetItemCost() throws Exception {}

    @Test
    public void testSubtractMoney() throws Exception {}

}