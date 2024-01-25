package org.example.utillity;

import java.math.BigDecimal;

public class BigDecValidator {
    public static BigDecimal asignZeroIfLessThanOne(BigDecimal passedValue) {
        BigDecimal result;
        int lessThan1 = passedValue.compareTo(BigDecimal.ONE);
        if (lessThan1 < 0) {
            result = BigDecimal.ZERO;
        } else result = passedValue;
        return result;
    }

    public static boolean isFirstSmallerThanSecond(BigDecimal first, BigDecimal second) {
        boolean result;

        int lessThan1 = first.compareTo(second);
        result = lessThan1 < 0;
        return result;
    }
}
