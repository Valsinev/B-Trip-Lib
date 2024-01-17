package org.example.utillity;

import org.example.constants.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseCalculatorTest {

    @Test
    void calculateTotalFuelPrice() {
        Assertions.assertEquals(ExpenseCalculator.calculateTotalFuelPrice("100", "10", "3.00"), "30.00");
        Assertions.assertEquals(ExpenseCalculator.calculateTotalFuelPrice(" ", "10", "3.00"), "0.0");
        Assertions.assertEquals(ExpenseCalculator.calculateTotalFuelPrice("100", " ", "3.00"), "0.0");
        Assertions.assertEquals(ExpenseCalculator.calculateTotalFuelPrice("100", "10", " "), "0.0");
        Assertions.assertEquals(ExpenseCalculator.calculateTotalFuelPrice(null, "10", " "), "0.0");
        Assertions.assertEquals(ExpenseCalculator.calculateTotalFuelPrice("100", null, "3.00"), "0.0");
        Assertions.assertEquals(ExpenseCalculator.calculateTotalFuelPrice("100", "10", null), "0.0");

    }

    @Test
    void calculateFuelConsumed() {
        Assertions.assertEquals(ExpenseCalculator.calculateFuelConsumed("100", "10"), "10");
        Assertions.assertEquals(ExpenseCalculator.calculateFuelConsumed("100", "0"), "0");
        Assertions.assertEquals(ExpenseCalculator.calculateFuelConsumed("100", "100"), "100");
        Assertions.assertEquals(ExpenseCalculator.calculateFuelConsumed("50", "2"), "1.0");
        Assertions.assertEquals(ExpenseCalculator.calculateFuelConsumed("50", " "), "0.0");
        Assertions.assertEquals(ExpenseCalculator.calculateFuelConsumed(" ", " "), "0.0");
        Assertions.assertEquals(ExpenseCalculator.calculateFuelConsumed(null, "10"), "0.0");
        Assertions.assertEquals(ExpenseCalculator.calculateFuelConsumed("100", null), "0.0");

    }

    @Test
    void calculateTotalExpenses() {
        Assertions.assertEquals(ExpenseCalculator.calculateTotalExpenses("200", "800", "100", "40"), "1140.00");
        Assertions.assertEquals(ExpenseCalculator.calculateTotalExpenses("", "800", "100", "40"), "940.00");
        Assertions.assertEquals(ExpenseCalculator.calculateTotalExpenses("200", "", "100", "40"), "340.00");
        Assertions.assertEquals(ExpenseCalculator.calculateTotalExpenses("200", "800", "", "40"), "1040.00");
        Assertions.assertEquals(ExpenseCalculator.calculateTotalExpenses("200", "800", "100", ""), "1100.00");
        Assertions.assertEquals(ExpenseCalculator.calculateTotalExpenses(" ", "800", "100", "40"), "940.00");
        Assertions.assertEquals(ExpenseCalculator.calculateTotalExpenses("200", " ", "100", "40"), "340.00");
        Assertions.assertEquals(ExpenseCalculator.calculateTotalExpenses("200", "800", " ", "40"), "1040.00");
        Assertions.assertEquals(ExpenseCalculator.calculateTotalExpenses("200", "800", "100", " "), "1100.00");
        Assertions.assertEquals(ExpenseCalculator.calculateTotalExpenses("100", "100", "100", "-100"), "300.00");
        Assertions.assertEquals(ExpenseCalculator.calculateTotalExpenses(null, "800", "100", "40"), "940.00");
        Assertions.assertEquals(ExpenseCalculator.calculateTotalExpenses("200", null, "100", "40"), "340.00");
        Assertions.assertEquals(ExpenseCalculator.calculateTotalExpenses("200", "800", null, "40"), "1040.00");
        Assertions.assertEquals(ExpenseCalculator.calculateTotalExpenses("200", "800", "100", null), "1100.00");

    }

    @Test
    void calcDailyMoney() {
        long dailyWithHotel = Config.DAILY_WITH_NIGHT_STAY;
        long dailyWithoutHotel = Config.DAILY_WITHOUT_NIGHT_STAY;
        Assertions.assertEquals(ExpenseCalculator.calcDailyMoney("10", "7"), String.valueOf(3 * dailyWithoutHotel + 7 * dailyWithHotel));
        Assertions.assertEquals(ExpenseCalculator.calcDailyMoney(" ", "7"), "0.0");
        Assertions.assertEquals(ExpenseCalculator.calcDailyMoney("10", " "), String.valueOf(10 * dailyWithoutHotel));
        Assertions.assertEquals(ExpenseCalculator.calcDailyMoney(null, "7"), "0.0");
        Assertions.assertEquals(ExpenseCalculator.calcDailyMoney("null", "7"), "0.0");
        Assertions.assertEquals(ExpenseCalculator.calcDailyMoney("10", null), String.valueOf(10 * dailyWithoutHotel));
        Assertions.assertEquals(ExpenseCalculator.calcDailyMoney("10", "null"), String.valueOf(10 * dailyWithoutHotel));
        Assertions.assertEquals(ExpenseCalculator.calcDailyMoney(null, null), "0.0");
    }

    @Test
    void calculateTotalNightStayExpense() {
        Assertions.assertEquals(ExpenseCalculator.calculateTotalNightStayExpense("5", "50"), "250");
        Assertions.assertEquals(ExpenseCalculator.calculateTotalNightStayExpense(" ", "50"), "0");
        Assertions.assertEquals(ExpenseCalculator.calculateTotalNightStayExpense(null, "50"), "0");
        Assertions.assertEquals(ExpenseCalculator.calculateTotalNightStayExpense("null", "50"), "0");
        Assertions.assertEquals(ExpenseCalculator.calculateTotalNightStayExpense("5", " "), "0");
        Assertions.assertEquals(ExpenseCalculator.calculateTotalNightStayExpense("5", null), "0");
        Assertions.assertEquals(ExpenseCalculator.calculateTotalNightStayExpense("5", "null"), "0");
        Assertions.assertEquals(ExpenseCalculator.calculateTotalNightStayExpense("5", "50"), "250");
    }
}