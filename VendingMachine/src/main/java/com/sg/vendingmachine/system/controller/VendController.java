package com.sg.vendingmachine.system.controller;

import com.sg.vendingmachine.inventory.InventoryService;
import com.sg.vendingmachine.transaction.Change;
import com.sg.vendingmachine.audit.AuditPersistenceException;
import com.sg.vendingmachine.inventory.InventoryPersistenceException;
import com.sg.vendingmachine.inventory.ItemDto;
import com.sg.vendingmachine.inventory.InventoryServiceImpl;
import com.sg.vendingmachine.system.ui.VendView;
import com.sg.vendingmachine.transaction.InsufficientFundsException;
import com.sg.vendingmachine.transaction.NoStockException;
import com.sg.vendingmachine.transaction.TransactionService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

public class VendController {

    private final VendView view;
    private final InventoryService inventoryServiceImpl;
    private final TransactionService transactionService;

    public VendController(InventoryService inventoryServiceImpl, VendView view, TransactionService transactionService) {
        this.view = view;
        this.inventoryServiceImpl = inventoryServiceImpl;
        this.transactionService = transactionService;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection;

        try {
            while (keepGoing) {
                view.printWelcomeBanner();
                view.printInventory(inventoryServiceImpl.getAllItems());
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
        } catch (InventoryPersistenceException |
                NoStockException |
                InsufficientFundsException |
                AuditPersistenceException e) {
            view.printErrorMessage(e.getMessage());
        }

    }

    private void purchaseItem() throws InventoryPersistenceException,
            NoStockException,
            InsufficientFundsException,
            AuditPersistenceException {
        BigDecimal moneyInserted = view.promptMoneyInserted().setScale(2, RoundingMode.HALF_UP);

        List<ItemDto> allItems = inventoryServiceImpl.getAllItems();
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
