package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendPersistenceException;
import com.sg.vendingmachine.dto.ItemDto;
import com.sg.vendingmachine.service.*;
import com.sg.vendingmachine.ui.VendView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class VendController {

    private final VendView view;
    private final InventoryService inventoryService;
    private final TransactionService transactionService;

    public VendController(InventoryService inventoryService, VendView view, TransactionService transactionService) {
        this.view = view;
        this.inventoryService = inventoryService;
        this.transactionService = transactionService;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection;

        try {
            while (keepGoing) {
                view.printWelcomeBanner();
                view.printItems(inventoryService.getAllItems());
                menuSelection = view.printMenuAndGetSelection();

                switch (menuSelection) {
                    case 1:
                        purchaseItem();
                        break;
                    case 2:
                        keepGoing = false;
                        break;
                    default:
                        unknown();
                }
            }

            exitMessage();
        } catch (VendPersistenceException |
                NoStockException |
                InsufficientFundsException e) {
            view.printErrorMessage(e.getMessage());
        }

    }

    private void purchaseItem() throws VendPersistenceException,
            NoStockException,
            InsufficientFundsException {
        BigDecimal moneyInserted = view.promptMoneyInserted().setScale(2, RoundingMode.HALF_UP);
        int itemSelection;
        boolean itemPurchased = false;
        List<ItemDto> allItems;

        while (true) {
            allItems = inventoryService.getAllItems();
            view.printItems(allItems);
            view.printMoney(moneyInserted);
            itemSelection = view.printItemsAndGetSelection(allItems);
            if (itemSelection == allItems.size() + 1) {
                break;
            }

            String itemId = String.valueOf(itemSelection);
            moneyInserted = subtractMoney(moneyInserted, itemId);
            itemPurchased = true;
        }

        if (itemPurchased) {
            view.printChange(transactionService.getChange(moneyInserted));
        }
    }

    private BigDecimal subtractMoney(BigDecimal moneyInserted, String itemId) throws VendPersistenceException, NoStockException, InsufficientFundsException {
        return transactionService.subtractMoney(moneyInserted, itemId);
    }

    private void unknown() {
        view.printUnknown();
    }

    private void exitMessage() {
        view.printExitMessage();
    }
}
