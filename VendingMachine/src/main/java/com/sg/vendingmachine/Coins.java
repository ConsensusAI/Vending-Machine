package com.sg.vendingmachine;

public enum Coins {
    PENNY("1"),
    NICKEL("5"),
    DIME("10"),
    QUARTER("25");

    public String value;

    private Coins(String value) {this.value = value;}

    @Override
    public String toString() {
        return value;
    }
}
