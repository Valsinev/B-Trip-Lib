package org.example.utillity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;

class ExpenseCalculatorTest {



    @Test
    void testCalculateTotalFuelPrice() {

        BigDecimal expected = BigDecimal.valueOf(150).setScale(2, RoundingMode.FLOOR);
        BigDecimal result = ExpenseCalculator.calculateTotalFuelPrice(BigDecimal.valueOf(500), BigDecimal.valueOf(10), BigDecimal.valueOf(3)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalFuelPrice(BigDecimal.valueOf(0), BigDecimal.valueOf(10), BigDecimal.valueOf(3)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalFuelPrice(BigDecimal.valueOf(500), BigDecimal.valueOf(0), BigDecimal.valueOf(3)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalFuelPrice(BigDecimal.valueOf(500), BigDecimal.valueOf(10), BigDecimal.valueOf(0)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalFuelPrice(null, BigDecimal.valueOf(10), BigDecimal.valueOf(3)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalFuelPrice(BigDecimal.valueOf(500), null, BigDecimal.valueOf(3)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalFuelPrice(BigDecimal.valueOf(500), BigDecimal.valueOf(10), null).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalFuelPrice(BigDecimal.valueOf(-500), BigDecimal.valueOf(10), BigDecimal.valueOf(3)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalFuelPrice(BigDecimal.valueOf(500), BigDecimal.valueOf(-10), BigDecimal.valueOf(3)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalFuelPrice(BigDecimal.valueOf(500), BigDecimal.valueOf(10), BigDecimal.valueOf(-3)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(150).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalFuelPrice(BigDecimal.valueOf(500), BigDecimal.valueOf(10), BigDecimal.valueOf(3)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(306.65).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalFuelPrice(BigDecimal.valueOf(740), BigDecimal.valueOf(16), BigDecimal.valueOf(2.59)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(245.34).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalFuelPrice(BigDecimal.valueOf(810), BigDecimal.valueOf(13), BigDecimal.valueOf(2.33)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testCalculateFuelConsumed() {

        BigDecimal expected = BigDecimal.valueOf(50).setScale(2, RoundingMode.FLOOR);
        BigDecimal result = ExpenseCalculator.calculateFuelConsumed(BigDecimal.valueOf(500), BigDecimal.valueOf(10)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        result = ExpenseCalculator.calculateFuelConsumed(null, BigDecimal.valueOf(10)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.FLOOR), result);


        result = ExpenseCalculator.calculateFuelConsumed(BigDecimal.valueOf(500), null).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.FLOOR), result);

        expected = BigDecimal.valueOf(85.8).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateFuelConsumed(BigDecimal.valueOf(660), BigDecimal.valueOf(13)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateFuelConsumed(BigDecimal.valueOf(-50), BigDecimal.valueOf(10)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateFuelConsumed(BigDecimal.valueOf(500), BigDecimal.valueOf(-10)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testCalculateTotalExpenses() {

        BigDecimal expected = BigDecimal.valueOf(10).setScale(2, RoundingMode.FLOOR);
        BigDecimal result = ExpenseCalculator.calculateTotalExpenses(BigDecimal.valueOf(10), BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(10).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalExpenses(BigDecimal.valueOf(0), BigDecimal.valueOf(10), BigDecimal.valueOf(0), BigDecimal.valueOf(0)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(10).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalExpenses(BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(10), BigDecimal.valueOf(0)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(10).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalExpenses(BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(10)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(10).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalExpenses(null, BigDecimal.valueOf(10), BigDecimal.valueOf(0), BigDecimal.valueOf(0)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(10).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalExpenses(BigDecimal.valueOf(10), null, BigDecimal.valueOf(0), BigDecimal.valueOf(0)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(10).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalExpenses(BigDecimal.valueOf(0), BigDecimal.valueOf(0), null, BigDecimal.valueOf(10)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(10).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalExpenses(BigDecimal.valueOf(0), BigDecimal.valueOf(10), BigDecimal.valueOf(0), null).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalExpenses(null, null, null, null).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalExpenses(BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(10).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalExpenses(BigDecimal.valueOf(-10), BigDecimal.valueOf(10), BigDecimal.valueOf(0), BigDecimal.valueOf(0)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(10).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalExpenses(BigDecimal.valueOf(10), BigDecimal.valueOf(-10), BigDecimal.valueOf(0), BigDecimal.valueOf(0)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(10).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalExpenses(BigDecimal.valueOf(0), BigDecimal.valueOf(10), BigDecimal.valueOf(-100), BigDecimal.valueOf(0)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(10).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalExpenses(BigDecimal.valueOf(0), BigDecimal.valueOf(10), BigDecimal.valueOf(0), BigDecimal.valueOf(-10)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalExpenses(BigDecimal.valueOf(-10), BigDecimal.valueOf(-10), BigDecimal.valueOf(-10), BigDecimal.valueOf(-10)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(2405.86).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalExpenses(BigDecimal.valueOf(1100), BigDecimal.valueOf(105.86), BigDecimal.valueOf(1000), BigDecimal.valueOf(200)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testCalcDailyMoney() {

        BigDecimal expected = BigDecimal.valueOf(200).setScale(2, RoundingMode.FLOOR);
        BigDecimal result = ExpenseCalculator.calcDailyMoney(BigDecimal.valueOf(10), BigDecimal.valueOf(0)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calcDailyMoney(BigDecimal.valueOf(-10), BigDecimal.valueOf(10)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(200).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calcDailyMoney(BigDecimal.valueOf(10), BigDecimal.valueOf(-10)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calcDailyMoney(BigDecimal.valueOf(0), BigDecimal.valueOf(10)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(200).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calcDailyMoney(BigDecimal.valueOf(10), BigDecimal.valueOf(0)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calcDailyMoney(null, BigDecimal.valueOf(10)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(200).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calcDailyMoney(BigDecimal.valueOf(10), null).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(400).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calcDailyMoney(BigDecimal.valueOf(10), BigDecimal.valueOf(10)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(220).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calcDailyMoney(BigDecimal.valueOf(10), BigDecimal.valueOf(1)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(380).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calcDailyMoney(BigDecimal.valueOf(10), BigDecimal.valueOf(9)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calcDailyMoney(BigDecimal.valueOf(10), BigDecimal.valueOf(15)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testCalculateTotalNightStayExpense() {

        BigDecimal expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.FLOOR);
        BigDecimal result = ExpenseCalculator.calculateTotalNightStayExpense(BigDecimal.valueOf(10), BigDecimal.valueOf(0)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalNightStayExpense(BigDecimal.valueOf(0), BigDecimal.valueOf(10)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalNightStayExpense(null, BigDecimal.valueOf(50)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalNightStayExpense(BigDecimal.valueOf(20), null).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalNightStayExpense(BigDecimal.valueOf(0), BigDecimal.valueOf(0)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalNightStayExpense(BigDecimal.valueOf(20), BigDecimal.valueOf(-10)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalNightStayExpense(BigDecimal.valueOf(10), BigDecimal.valueOf(-20)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalNightStayExpense(BigDecimal.valueOf(-10), BigDecimal.valueOf(-10)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalNightStayExpense(null, null).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(200).setScale(2, RoundingMode.FLOOR);
        result = ExpenseCalculator.calculateTotalNightStayExpense(BigDecimal.valueOf(20), BigDecimal.valueOf(10)).setScale(2, RoundingMode.FLOOR);
        Assertions.assertEquals(expected, result);

    }
}