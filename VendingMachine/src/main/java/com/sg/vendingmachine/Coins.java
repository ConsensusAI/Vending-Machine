package com.sg.vendingmachine;

// After the changes I made, this enum is kind of unnecessary, you can just declare these values as constants in the ChangeService if you want
public enum Coins {
    PENNY("1"),
    NICKEL("5"),
    DIME("10"),
    QUARTER("25");

    private final String value;

    Coins(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
