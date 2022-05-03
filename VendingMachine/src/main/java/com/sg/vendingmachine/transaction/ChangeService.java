package com.sg.vendingmachine.transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ChangeService {
    private static final String QUARTER_VALUE = Coins.QUARTER.getValue();
    private static final String DIME_VALUE = Coins.DIME.getValue();
    private static final String NICKEL_VALUE = Coins.NICKEL.getValue();
    private static final String PENNY_VALUE = Coins.PENNY.getValue();

    public Change computeChange(BigDecimal amountToReturn) {
        BigDecimal amountInPennies = amountToReturn.multiply(new BigDecimal("100"));

        int numOfQuarters = computeQuarters(amountInPennies);
        amountInPennies = amountInPennies.subtract(new BigDecimal(numOfQuarters).multiply(new BigDecimal(QUARTER_VALUE)));

        int numOfDimes = computeDimes(amountInPennies);
        amountInPennies = amountInPennies.subtract(new BigDecimal(numOfDimes).multiply(new BigDecimal(DIME_VALUE)));

        int numOfNickels = computeNickels(amountInPennies);
        amountInPennies = amountInPennies.subtract(new BigDecimal(numOfNickels).multiply(new BigDecimal(NICKEL_VALUE)));

        int numOfPennies = computePennies(amountInPennies);

        return new Change(amountToReturn, numOfQuarters, numOfDimes, numOfNickels, numOfPennies);
    }

    private int computeQuarters(BigDecimal amountInPennies) {
        BigDecimal quarters = amountInPennies.divide(new BigDecimal(QUARTER_VALUE), 0, RoundingMode.DOWN);
        return quarters.intValue();
    }

    private int computeDimes(BigDecimal amountInPennies) {
        BigDecimal dimes = amountInPennies.divide(new BigDecimal(DIME_VALUE), 0, RoundingMode.DOWN);
        return dimes.intValue();
    }

    private int computeNickels(BigDecimal amountInPennies) {
        BigDecimal nickels = amountInPennies.divide(new BigDecimal(NICKEL_VALUE), 0, RoundingMode.DOWN);
        return nickels.intValue();
    }

    private int computePennies(BigDecimal amountInPennies) {
        BigDecimal pennies = amountInPennies.divide(new BigDecimal(PENNY_VALUE), 0, RoundingMode.DOWN);
        return pennies.intValue();
    }
}
