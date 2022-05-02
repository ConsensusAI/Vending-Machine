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
        view.printMoney(moneyInserted);
        view.printItems(allItems);
        int itemSelection;
        boolean keepGoing = true;

        while (keepGoing) {
            itemSelection = view.printItemsAndGetSelection(allItems);
            if (itemSelection == allItems.size() + 1) {
                keepGoing = false;
                break;
            }
            moneyInserted = dao.subtractMoney(moneyInserted, String.valueOf(itemSelection));
            view.printMoney(moneyInserted);
        }
    }

    private void unknown() {
        view.printUnknown();
    }

    private void exit() {
        view.printExitMessage();
    }
}
