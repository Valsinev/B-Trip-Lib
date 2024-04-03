package org.example.utillity;

import org.example.configuration.IConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExpenseCalculatorTest {


    public static IConfiguration configuration;
    public static ExpenseCalculator expenseCalculator;

    @BeforeAll
    static void setup() {
        RoundingMode rounding = RoundingMode.HALF_UP;
        configuration = mock(IConfiguration.class);
        when(configuration.getDailyMoneyWithHotel()).thenReturn(40L);
        when(configuration.getDailyMoneyWithoutHotel()).thenReturn(20L);
        when(configuration.getFont()).thenReturn(new Font("Arial", Font.ITALIC, 25));
        when(configuration.getNumberOfDaysInOneOrder()).thenReturn(8);
        when(configuration.getFontColor()).thenReturn(Color.RED);
        when(configuration.getScale()).thenReturn(2);
        when(configuration.getRoundingMode()).thenReturn(rounding);
        expenseCalculator = new ExpenseCalculator(configuration);
    }

    @Test
    void testCalculateTotalFuelPrice() {

        BigDecimal expected = BigDecimal.valueOf(150.00).setScale(configuration.getScale(), configuration.getRoundingMode());
        BigDecimal result = expenseCalculator.calculateTotalFuelPrice(BigDecimal.valueOf(500), BigDecimal.valueOf(10), BigDecimal.valueOf(3));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(configuration.getScale(), configuration.getRoundingMode());
        result = expenseCalculator.calculateTotalFuelPrice(BigDecimal.valueOf(0), BigDecimal.valueOf(10), BigDecimal.valueOf(3));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(19.695).setScale(configuration.getScale(), configuration.getRoundingMode());
        result = expenseCalculator.calculateTotalFuelPrice(BigDecimal.valueOf(65), BigDecimal.valueOf(10), BigDecimal.valueOf(3.03));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(configuration.getScale(), configuration.getRoundingMode());
        result = expenseCalculator.calculateTotalFuelPrice(BigDecimal.valueOf(500), BigDecimal.valueOf(0), BigDecimal.valueOf(3));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(configuration.getScale(), configuration.getRoundingMode());;
        result = expenseCalculator.calculateTotalFuelPrice(BigDecimal.valueOf(500), BigDecimal.valueOf(10), BigDecimal.valueOf(0));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(configuration.getScale(), configuration.getRoundingMode());;
        result = expenseCalculator.calculateTotalFuelPrice(null, BigDecimal.valueOf(10), BigDecimal.valueOf(3));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(configuration.getScale(), configuration.getRoundingMode());;
        result = expenseCalculator.calculateTotalFuelPrice(BigDecimal.valueOf(500), null, BigDecimal.valueOf(3));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(configuration.getScale(), configuration.getRoundingMode());;
        result = expenseCalculator.calculateTotalFuelPrice(BigDecimal.valueOf(500), BigDecimal.valueOf(10), null);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(configuration.getScale(), configuration.getRoundingMode());;
        result = expenseCalculator.calculateTotalFuelPrice(BigDecimal.valueOf(-500), BigDecimal.valueOf(10), BigDecimal.valueOf(3));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(configuration.getScale(), configuration.getRoundingMode());;
        result = expenseCalculator.calculateTotalFuelPrice(BigDecimal.valueOf(500), BigDecimal.valueOf(-10), BigDecimal.valueOf(3));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(configuration.getScale(), configuration.getRoundingMode());;
        result = expenseCalculator.calculateTotalFuelPrice(BigDecimal.valueOf(500), BigDecimal.valueOf(10), BigDecimal.valueOf(-3));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(150.00).setScale(configuration.getScale(), configuration.getRoundingMode());;
        result = expenseCalculator.calculateTotalFuelPrice(BigDecimal.valueOf(500), BigDecimal.valueOf(10), BigDecimal.valueOf(3));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(306.656).setScale(configuration.getScale(), configuration.getRoundingMode());;
        result = expenseCalculator.calculateTotalFuelPrice(BigDecimal.valueOf(740), BigDecimal.valueOf(16), BigDecimal.valueOf(2.59));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(245.349).setScale(configuration.getScale(), configuration.getRoundingMode());;
        result = expenseCalculator.calculateTotalFuelPrice(BigDecimal.valueOf(810), BigDecimal.valueOf(13), BigDecimal.valueOf(2.33));
        Assertions.assertEquals(expected, result);


        expected = BigDecimal.valueOf(412.128).setScale(configuration.getScale(), configuration.getRoundingMode());;
        result = expenseCalculator.calculateTotalFuelPrice(BigDecimal.valueOf(810), BigDecimal.valueOf(16), BigDecimal.valueOf(3.18));
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testCalculateFuelConsumed() {

        BigDecimal expected = BigDecimal.valueOf(50).setScale(configuration.getScale());
        BigDecimal result = expenseCalculator.calculateFuelConsumed(BigDecimal.valueOf(500), BigDecimal.valueOf(10));
        Assertions.assertEquals(expected, result);

        result = expenseCalculator.calculateFuelConsumed(null, BigDecimal.valueOf(10));
        Assertions.assertEquals(BigDecimal.ZERO.setScale(configuration.getScale()), result);


        result = expenseCalculator.calculateFuelConsumed(BigDecimal.valueOf(500), null);
        Assertions.assertEquals(BigDecimal.ZERO.setScale(configuration.getScale()), result);

        expected = BigDecimal.valueOf(85.80).setScale(configuration.getScale());
        result = expenseCalculator.calculateFuelConsumed(BigDecimal.valueOf(660), BigDecimal.valueOf(13));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(configuration.getScale());
        result = expenseCalculator.calculateFuelConsumed(BigDecimal.valueOf(-50), BigDecimal.valueOf(10));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0).setScale(configuration.getScale());
        result = expenseCalculator.calculateFuelConsumed(BigDecimal.valueOf(500), BigDecimal.valueOf(-10));
        Assertions.assertEquals(expected, result);
    }

    @Deprecated
    @Test
    void testCalculateTotalExpenses() {

        BigDecimal expected = BigDecimal.valueOf(10);
        BigDecimal result = expenseCalculator.calculateTotalExpenses(BigDecimal.valueOf(10), BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(10);
        result = expenseCalculator.calculateTotalExpenses(BigDecimal.valueOf(0), BigDecimal.valueOf(10), BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(10);
        result = expenseCalculator.calculateTotalExpenses(BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(10), BigDecimal.valueOf(0));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(10);
        result = expenseCalculator.calculateTotalExpenses(BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(10));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(10);
        result = expenseCalculator.calculateTotalExpenses(null, BigDecimal.valueOf(10), BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(10);
        result = expenseCalculator.calculateTotalExpenses(BigDecimal.valueOf(10), null, BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(10);
        result = expenseCalculator.calculateTotalExpenses(BigDecimal.valueOf(0), BigDecimal.valueOf(0), null, BigDecimal.valueOf(10));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(10);
        result = expenseCalculator.calculateTotalExpenses(BigDecimal.valueOf(0), BigDecimal.valueOf(10), BigDecimal.valueOf(0), null);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0);
        result = expenseCalculator.calculateTotalExpenses(null, null, null, null);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0);
        result = expenseCalculator.calculateTotalExpenses(BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(10);
        result = expenseCalculator.calculateTotalExpenses(BigDecimal.valueOf(-10), BigDecimal.valueOf(10), BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(10);
        result = expenseCalculator.calculateTotalExpenses(BigDecimal.valueOf(10), BigDecimal.valueOf(-10), BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(10);
        result = expenseCalculator.calculateTotalExpenses(BigDecimal.valueOf(0), BigDecimal.valueOf(10), BigDecimal.valueOf(-100), BigDecimal.valueOf(0));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(10);
        result = expenseCalculator.calculateTotalExpenses(BigDecimal.valueOf(0), BigDecimal.valueOf(10), BigDecimal.valueOf(0), BigDecimal.valueOf(-10));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0);
        result = expenseCalculator.calculateTotalExpenses(BigDecimal.valueOf(-10), BigDecimal.valueOf(-10), BigDecimal.valueOf(-10), BigDecimal.valueOf(-10));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(2405.86);
        result = expenseCalculator.calculateTotalExpenses(BigDecimal.valueOf(1100), BigDecimal.valueOf(105.86), BigDecimal.valueOf(1000), BigDecimal.valueOf(200));
        Assertions.assertEquals(expected, result);
    }

    @Deprecated
    @Test
    void testCalcDailyMoney() {

        BigDecimal expected = BigDecimal.valueOf(200);
        BigDecimal result = expenseCalculator.calcDailyMoney(BigDecimal.valueOf(10), BigDecimal.valueOf(0), configuration);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0);
        result = expenseCalculator.calcDailyMoney(BigDecimal.valueOf(-10), BigDecimal.valueOf(10), configuration);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(200);
        result = expenseCalculator.calcDailyMoney(BigDecimal.valueOf(10), BigDecimal.valueOf(-10), configuration);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0);
        result = expenseCalculator.calcDailyMoney(BigDecimal.valueOf(0), BigDecimal.valueOf(10), configuration);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(200);
        result = expenseCalculator.calcDailyMoney(BigDecimal.valueOf(10), BigDecimal.valueOf(0), configuration);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0);
        result = expenseCalculator.calcDailyMoney(null, BigDecimal.valueOf(10), configuration);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(200);
        result = expenseCalculator.calcDailyMoney(BigDecimal.valueOf(10), null, configuration);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(400);
        result = expenseCalculator.calcDailyMoney(BigDecimal.valueOf(10), BigDecimal.valueOf(10), configuration);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(220);
        result = expenseCalculator.calcDailyMoney(BigDecimal.valueOf(10), BigDecimal.valueOf(1), configuration);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(380);
        result = expenseCalculator.calcDailyMoney(BigDecimal.valueOf(10), BigDecimal.valueOf(9), configuration);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0);
        result = expenseCalculator.calcDailyMoney(BigDecimal.valueOf(10), BigDecimal.valueOf(15), configuration);
        Assertions.assertEquals(expected, result);
    }

    @Deprecated
    @Test
    void testCalculateTotalNightStayExpense() {

        BigDecimal expected = BigDecimal.valueOf(0);
        BigDecimal result = expenseCalculator.calculateTotalNightStayExpense(BigDecimal.valueOf(10), BigDecimal.valueOf(0));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0);
        result = expenseCalculator.calculateTotalNightStayExpense(BigDecimal.valueOf(0), BigDecimal.valueOf(10));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0);
        result = expenseCalculator.calculateTotalNightStayExpense(null, BigDecimal.valueOf(50));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0);
        result = expenseCalculator.calculateTotalNightStayExpense(BigDecimal.valueOf(20), null);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0);
        result = expenseCalculator.calculateTotalNightStayExpense(BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0);
        result = expenseCalculator.calculateTotalNightStayExpense(BigDecimal.valueOf(20), BigDecimal.valueOf(-10));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0);
        result = expenseCalculator.calculateTotalNightStayExpense(BigDecimal.valueOf(10), BigDecimal.valueOf(-20));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0);
        result = expenseCalculator.calculateTotalNightStayExpense(BigDecimal.valueOf(-10), BigDecimal.valueOf(-10));
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(0);
        result = expenseCalculator.calculateTotalNightStayExpense(null, null);
        Assertions.assertEquals(expected, result);

        expected = BigDecimal.valueOf(200);
        result = expenseCalculator.calculateTotalNightStayExpense(BigDecimal.valueOf(20), BigDecimal.valueOf(10));
        Assertions.assertEquals(expected, result);

    }
}