package com.sg.vendingmachine;

public enum Coins {
    PENNY("1"),
    NICKEL("5"),
    DIME("10"),
    QUARTER("25");

    private final String value;

    Coins(String value) {this.value = value;}

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
