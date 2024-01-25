package org.example.utillity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class BigDecValidatorTest {

    @Test
    void asignZeroIfLessThanOne() {
        BigDecimal expected = BigDecimal.ZERO;
        BigDecimal actual = BigDecValidator.asignZeroIfLessThanOne(BigDecimal.valueOf(-1));
        Assertions.assertEquals(expected.setScale(2, RoundingMode.FLOOR), actual.setScale(2, RoundingMode.FLOOR));

        expected = BigDecimal.ZERO;
        actual = BigDecValidator.asignZeroIfLessThanOne(BigDecimal.valueOf(0));
        Assertions.assertEquals(expected.setScale(2, RoundingMode.FLOOR), actual.setScale(2, RoundingMode.FLOOR));

        expected = BigDecimal.valueOf(5);
        actual = BigDecValidator.asignZeroIfLessThanOne(BigDecimal.valueOf(5));
        Assertions.assertEquals(expected.setScale(2, RoundingMode.FLOOR), actual.setScale(2, RoundingMode.FLOOR));
    }

    @Test
    void isFirstSmallerThanSecond() {
        BigDecimal decimal1 = BigDecimal.valueOf(0);
        BigDecimal decimal2 = BigDecimal.valueOf(1);
        boolean result = BigDecValidator.isFirstSmallerThanSecond(decimal1, decimal2);
        Assertions.assertTrue(result);

        decimal1 = BigDecimal.valueOf(5);
        decimal2 = BigDecimal.valueOf(5);
        result = BigDecValidator.isFirstSmallerThanSecond(decimal1, decimal2);
        Assertions.assertFalse(result);

        decimal1 = BigDecimal.valueOf(5);
        decimal2 = BigDecimal.valueOf(2);
        result = BigDecValidator.isFirstSmallerThanSecond(decimal1, decimal2);
        Assertions.assertFalse(result);

        decimal1 = BigDecimal.valueOf(5);
        decimal2 = BigDecimal.valueOf(-2);
        result = BigDecValidator.isFirstSmallerThanSecond(decimal1, decimal2);
        Assertions.assertFalse(result);
    }
}