package com.sg.vendingmachine.transaction;

public enum Coins {
    PENNY("1", "Pennies"),
    NICKEL("5", "Nickels"),
    DIME("10", "Dimes"),
    QUARTER("25", "Quarters");

    private final String value;
    private final String name;

    Coins(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
