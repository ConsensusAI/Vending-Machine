package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendDao;
import com.sg.vendingmachine.dto.ItemDto;
import com.sg.vendingmachine.ui.VendView;

import java.math.BigDecimal;

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

        while (keepGoing) {

        }
    }
}
