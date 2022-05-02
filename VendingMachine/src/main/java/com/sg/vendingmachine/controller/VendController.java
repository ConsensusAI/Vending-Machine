package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendPersistenceException;
import com.sg.vendingmachine.dto.ItemDto;
import com.sg.vendingmachine.service.VendServiceLayer;
import com.sg.vendingmachine.ui.VendView;

import java.math.BigDecimal;
import java.util.List;

public class VendController {

    private VendView view;
    private VendServiceLayer service;

    public VendController(VendServiceLayer service, VendView view) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection;

        try {
            while (keepGoing) {
                view.printWelcomeBanner();
                view.printItems(service.getAllItems());
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
        } catch (VendPersistenceException e) {
            view.printErrorMessage(e.getMessage());
        }

    }

    private void purchaseItem() throws VendPersistenceException{
        BigDecimal moneyInserted = view.promptMoneyInserted().setScale(2);
        int itemSelection;
        boolean keepGoing = true;
        List<ItemDto> allItems;

        while (keepGoing) {
            allItems = service.getAllItems();
            view.printItems(allItems);
            view.printMoney(moneyInserted);
            itemSelection = view.printItemsAndGetSelection(allItems);
            if (itemSelection == allItems.size() + 1) {
                keepGoing = false;
                break;
            }

            String itemId = String.valueOf(itemSelection);
            boolean hasStock = checkStock(itemId);
            if (!hasStock) {
                view.printOutOfStock();
            } else {
                boolean canAfford = compareMoney(moneyInserted, String.valueOf(itemSelection));
                if (canAfford) {
                    moneyInserted = subtractMoney(moneyInserted, itemId);
                    view.printMoney(moneyInserted);
                } else {
                    view.printCannotAfford();
                }
            }
        }
    }

    private boolean compareMoney(BigDecimal userMoney, String itemId) throws VendPersistenceException {
        return service.compareMoney(userMoney, itemId);
    }

    private boolean checkStock(String itemId) throws VendPersistenceException {
        return service.checkStock(itemId);
    }

    private BigDecimal subtractMoney(BigDecimal moneyInserted, String itemId) throws VendPersistenceException {
        return service.subtractMoney(moneyInserted, itemId);
    }

    private void unknown() {
        view.printUnknown();
    }

    private void exitMessage() {
        view.printExitMessage();
    }
}
