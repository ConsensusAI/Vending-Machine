package com.sg.vendingmachine.service;

import com.sg.vendingmachine.inventory.InventoryDao;
import com.sg.vendingmachine.inventory.InventoryService;
import com.sg.vendingmachine.inventory.ItemDto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {

    private InventoryService inventoryService;
    private InventoryDao inventoryDao;

    public InventoryServiceTest() {
        InventoryDao inventoryDao = new InventoryDaoStubImpl();
        inventoryService = new InventoryService(inventoryDao);
    }

    @Test
    public void testGetAllItems() throws Exception {
        ItemDto testClone = new ItemDto("1");
        testClone.setName("Chips");
        testClone.setCost(new BigDecimal("9.99"));
        testClone.setStock(10);

        List<ItemDto> allItems = inventoryService.getAllItems();
        assertEquals(allItems.size(), 1, "List should contain only 1 item.");
        assertTrue(allItems.contains(testClone), "The list should contain Chips.");
    }

    @Test
    public void testGetItem() throws Exception {
        ItemDto testClone = new ItemDto("1");
        testClone.setName("Chips");
        testClone.setCost(new BigDecimal("9.99"));
        testClone.setStock(10);

        ItemDto retrievedItem = inventoryService.getItem("1");

        assertNotNull(retrievedItem, "Item should not be null.");
        assertEquals(testClone, retrievedItem, "Item should be Chips.");

        ItemDto nullItem = inventoryService.getItem("10");
        assertNull(nullItem, "Getting ID 10 should be null.");
    }

    @Test
    public void testReduceItemStock() throws Exception {
        inventoryService.reduceItemStock("1");
        ItemDto testItem = inventoryService.getItem("1");
        assertEquals(testItem.getStock(), 9, "Stock should be reduced to 9.");
    }
}