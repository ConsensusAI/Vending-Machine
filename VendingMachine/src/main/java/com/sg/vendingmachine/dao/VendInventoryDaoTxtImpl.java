package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.ItemDto;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class VendInventoryDaoTxtImpl implements VendInventoryDao {

    private Map<String, ItemDto> inventory = new HashMap<>();
    private final String INVENTORY_FILE;
    private static final String DELIMITER = "::";

    public VendInventoryDaoTxtImpl() {
        INVENTORY_FILE = "inventory.txt";
    }

    public VendInventoryDaoTxtImpl(String inventoryTextFile) {
        this.INVENTORY_FILE = inventoryTextFile;
    }

    public ItemDto addItem(String id, ItemDto item) throws VendPersistenceException {
        loadInventory();
        ItemDto newItem = inventory.put(id, item);
        writeInventory();
        return newItem;
    }

    private ItemDto unmarshallItem(String itemAsText) {
        /*
         _______________________________
         |   |             |      |    |
         | 1 |Chocolate Bar| 1.25 | 12 |
         |   |             |      |    |
         -------------------------------
         [0]       [1]       [2]   [3]
         */

        String[] itemTokens = itemAsText.split(DELIMITER);
        String id = itemTokens[0];
        ItemDto itemFromFile = new ItemDto(id);

        itemFromFile.setName(itemTokens[1]);
        BigDecimal cost = new BigDecimal(itemTokens[2]);
        itemFromFile.setCost(cost);
        int stock = Integer.parseInt(itemTokens[3]);
        itemFromFile.setStock(stock);

        return itemFromFile;
    }

    private void loadInventory() throws VendPersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendPersistenceException("Could not load inventory data into memory", e);
        }
        String currentLine;
        ItemDto currentItem;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);
            inventory.put(currentItem.getId(), currentItem);
        }
        scanner.close();
    }

    private String marshallItem(ItemDto item) {
        // 1::Chocolate Bar::1.25::12
        String itemAsText = item.getId() + DELIMITER;
        itemAsText += item.getName() + DELIMITER;
        itemAsText += item.getCost() + DELIMITER;
        itemAsText += item.getStock();

        return itemAsText;
    }

    private void writeInventory() throws VendPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new VendPersistenceException("Could not save inventory data.");
        }

        String itemAsText;
        List<ItemDto> items = this.getAllItems();
        for (ItemDto currentItem : items) {
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
            out.flush();
        }
        out.close();
    }

    @Override
    public List<ItemDto> getAllItems() throws VendPersistenceException {
        loadInventory();
        return new ArrayList<ItemDto>(inventory.values());
    }

    @Override
    public ItemDto getItem(String id) throws VendPersistenceException {
        loadInventory();
        return inventory.get(id);
    }

    @Override
    public int getItemStock(String id) throws VendPersistenceException {
        loadInventory();
        return inventory.get(id).getStock();
    }

    @Override
    public void reduceItemStock(String id) throws VendPersistenceException {
        loadInventory();
        inventory.get(id).reduceStock();
        writeInventory();
    }

    @Override
    public BigDecimal getItemCost(String id) throws VendPersistenceException {
        loadInventory();
        return inventory.get(id).getCost();
    }

    @Override
    public BigDecimal subtractMoney(BigDecimal initMoney, String itemId) {
        BigDecimal cost = inventory.get(itemId).getCost();
        return initMoney.subtract(cost);
    }
}
