package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendDao;
import com.sg.vendingmachine.ui.VendView;

public class VendController {

    private VendView view;
    private VendDao dao;

    public VendController(VendView view, VendDao dao) {
        this.view = view;
        this.dao = dao;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection;
        int itemSelection;

        while (keepGoing) {
            view.printWelcomeBanner();
            view.printItems(dao.getAllItems());
            menuSelection = view.printMenuAndGetSelection();

            switch (menuSelection) {
                case 1:
                    System.out.println("PURCHASE ITEM");
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

    private void unknown() {
        view.printUnknown();
    }

    private void exit() {
        view.printExitMessage();
    }
}
