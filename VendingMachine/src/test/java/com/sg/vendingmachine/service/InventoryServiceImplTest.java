package com.sg.vendingmachine.service;

import com.sg.vendingmachine.inventory.InventoryDao;
import com.sg.vendingmachine.inventory.InventoryServiceImpl;
import com.sg.vendingmachine.inventory.ItemDto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceImplTest {

    private InventoryServiceImpl inventoryServiceImpl;
    private InventoryDao inventoryDao;

    public InventoryServiceImplTest() {
        InventoryDao inventoryDao = new InventoryDaoStubImpl();
        inventoryServiceImpl = new InventoryServiceImpl(inventoryDao);
    }

    @Test
    public void testGetAllItems() throws Exception {
        ItemDto testClone = new ItemDto("1");
        testClone.setName("Chips");
        testClone.setCost(new BigDecimal("9.99"));
        testClone.setStock(10);

        List<ItemDto> allItems = inventoryServiceImpl.getAllItems();
        assertEquals(allItems.size(), 1, "List should contain only 1 item.");
        assertTrue(allItems.contains(testClone), "The list should contain Chips.");
    }

    @Test
    public void testGetItem() throws Exception {
        ItemDto testClone = new ItemDto("1");
        testClone.setName("Chips");
        testClone.setCost(new BigDecimal("9.99"));
        testClone.setStock(10);

        ItemDto retrievedItem = inventoryServiceImpl.getItem("1");

        assertNotNull(retrievedItem, "Item should not be null.");
        assertEquals(testClone, retrievedItem, "Item should be Chips.");

        ItemDto nullItem = inventoryServiceImpl.getItem("10");
        assertNull(nullItem, "Getting ID 10 should be null.");
    }

    @Test
    public void testReduceItemStock() throws Exception {
        inventoryServiceImpl.reduceItemStock("1");
        ItemDto testItem = inventoryServiceImpl.getItem("1");
        assertEquals(testItem.getStock(), 9, "Stock should be reduced to 9.");
    }
}