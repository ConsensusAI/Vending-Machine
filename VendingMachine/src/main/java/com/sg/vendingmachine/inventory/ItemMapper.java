package com.sg.vendingmachine.inventory;

import java.math.BigDecimal;

class ItemMapper {

    private static final String DELIMITER = "::";

    private ItemMapper() {
    }

    static ItemDto toDto(String itemAsText) {
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

    static String toText(ItemDto item) {
        // Converts ItemDto to text with format: 1::Chocolate Bar::1.25::12
        String itemAsText = item.getId() + DELIMITER;
        itemAsText += item.getName() + DELIMITER;
        itemAsText += item.getCost() + DELIMITER;
        itemAsText += item.getStock();

        return itemAsText;
    }
}
