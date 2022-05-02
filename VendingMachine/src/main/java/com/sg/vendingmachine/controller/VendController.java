package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendDao;
import com.sg.vendingmachine.dto.ItemDto;
import com.sg.vendingmachine.ui.VendView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class VendController {

    private VendView view;
    private VendDao dao;
    private Map<String, ItemDto> allItems;

    public VendController(VendView view, VendDao dao) {
        this.view = view;
        this.dao = dao;
        allItems = dao.getAllItems();
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection;

        while (keepGoing) {
            view.printWelcomeBanner();
            view.printItems(allItems);
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

        exit();
    }

    private void purchaseItem() {
        BigDecimal moneyInserted = view.promptMoneyInserted();
        view.printItems(allItems);
        int itemSelection;
        boolean keepGoing = true;

        while (keepGoing) {
            itemSelection = view.printItemsAndGetSelection(allItems);
            switch (itemSelection) {
                case 1:
                    System.out.println("Chocolate bar purchased.");
                    break;
                case 2:
                    System.out.println("Sports drink purchased.");
                    break;
                case 3:
                    System.out.println("Water bottle purchased.");
                    break;
                case 4:
                    System.out.println("Granola bar purchased.");
                    break;
                case 5:
                    System.out.println("Potato chips purchased.");
                    break;
                case 6:
                    keepGoing = false;
                default:
                    unknown();
            }
        }
    }

    private void unknown() {
        view.printUnknown();
    }

    private void exit() {
        view.printExitMessage();
    }
}
