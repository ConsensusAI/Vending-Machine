package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.Change;
import com.sg.vendingmachine.dao.AuditPersistenceException;
import com.sg.vendingmachine.dao.InventoryPersistenceException;
import com.sg.vendingmachine.dto.ItemDto;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.InventoryService;
import com.sg.vendingmachine.service.NoStockException;
import com.sg.vendingmachine.service.TransactionService;
import com.sg.vendingmachine.ui.VendView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

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
                view.printInventory(inventoryService.getAllItems());
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
        } catch (InventoryPersistenceException | NoStockException | InsufficientFundsException | AuditPersistenceException e) {
            view.printErrorMessage(e.getMessage());
        }
    }

    private void purchaseItem()
            throws InventoryPersistenceException, NoStockException, InsufficientFundsException, AuditPersistenceException {
        BigDecimal moneyInserted = view.promptMoneyInserted().setScale(2, RoundingMode.HALF_UP);

        List<ItemDto> allItems = inventoryService.getAllItems();
        view.printMoney(moneyInserted);
        int itemSelection = view.printItemsAndGetSelection(allItems);
        if (itemSelection == allItems.size() + 1) {
            return;
        }

        String itemId = String.valueOf(itemSelection);
        Optional<Change> changeMaybe = transactionService.computeTransaction(moneyInserted, itemId);
        changeMaybe.ifPresentOrElse(view::printChange, view::printNoChange);
    }

    private void unknown() {
        view.printUnknown();
    }

    private void exitMessage() {
        view.printExitMessage();
    }
}
