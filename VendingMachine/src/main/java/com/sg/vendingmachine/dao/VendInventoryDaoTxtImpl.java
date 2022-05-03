package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.ItemDto;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class VendInventoryDaoTxtImpl implements VendInventoryDao {

    private final Map<String, ItemDto> inventory = new HashMap<>();
    private final String inventoryFile;

    public VendInventoryDaoTxtImpl() {
        inventoryFile = "inventory.txt";
    }

    public VendInventoryDaoTxtImpl(String inventoryTextFile) {
        this.inventoryFile = inventoryTextFile;
    }

    @Override
    public List<ItemDto> getAllItems() throws InventoryPersistenceException {
        loadInventory();
        return new ArrayList<>(inventory.values());
    }

    @Override
    public ItemDto getItem(String id) throws InventoryPersistenceException {
        loadInventory();
        return inventory.get(id);
    }

    @Override
    public void updateItem(String id, ItemDto item) throws InventoryPersistenceException {
        loadInventory();
        inventory.put(id, item);
        writeInventory();
    }

    private void loadInventory() throws InventoryPersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(inventoryFile)));
        } catch (FileNotFoundException e) {
            throw new InventoryPersistenceException("Could not load inventory data into memory", e);
        }
        String currentLine;
        ItemDto currentItem;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = ItemMapper.toDto(currentLine);
            inventory.put(currentItem.getId(), currentItem);
        }
        scanner.close();
    }

    private void writeInventory() throws InventoryPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(inventoryFile));
        } catch (IOException e) {
            throw new InventoryPersistenceException("Could not save inventory data.");
        }

        String itemAsText;
        List<ItemDto> items = this.getAllItems();
        for (ItemDto currentItem : items) {
            itemAsText = ItemMapper.toText(currentItem);
            out.println(itemAsText);
            out.flush();
        }
        out.close();
    }
}
