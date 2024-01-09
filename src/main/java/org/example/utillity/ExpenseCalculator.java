package org.example.utillity;

import org.example.constants.Config;

import java.math.BigDecimal;

public class ExpenseCalculator {
    public static String calculateTotalFuelPrice(String kilometers, String costBy100, String fuelPrice) {
        return String.format("%.2f", new BigDecimal(calculateFuelConsumed(kilometers, costBy100)).multiply(new BigDecimal(fuelPrice)));
    }

    public static String calculateFuelConsumed(String kilometers, String costBy100) {
        return new BigDecimal(kilometers).divide(BigDecimal.valueOf(100.0)).multiply(new BigDecimal(costBy100)).toString();
    }

    public static String calculateTotalExpenses(String hotelExpenses, String dailyExpenses, String totalFuelExpenses, String addExpenses) {
        BigDecimal hotelTotal = hotelExpenses.isEmpty() ? BigDecimal.ZERO : new BigDecimal(hotelExpenses);
            return String.format("%.2f",
                    hotelTotal
                    .add(new BigDecimal(dailyExpenses))
                    .add(new BigDecimal(totalFuelExpenses))
                    .add(new BigDecimal(addExpenses)));

    }

    public static String calcDailyMoney(String numberOfDays, String daysInHotel) {
        //nights in hotel * daily money for nightstay + (total days - nights in hotel = nights without hotel * daily money without night stay)
        BigDecimal dailyMoneyWithNightStay = new BigDecimal(daysInHotel).multiply(BigDecimal.valueOf(Config.DAILY_WITH_NIGHT_STAY));
        BigDecimal dailyMoneyWithoutNightStay = (new BigDecimal(numberOfDays).subtract(new BigDecimal(daysInHotel))).multiply(BigDecimal.valueOf(Config.DAILY_WITHOUT_NIGHT_STAY));
        return dailyMoneyWithNightStay.add(dailyMoneyWithoutNightStay).toString();
    }

    public static String calculateTotalNightStayExpense(String numberOfNightsStayed, String nightStayPrice) {
        BigDecimal total = new BigDecimal(numberOfNightsStayed).multiply(new BigDecimal(nightStayPrice));
        return total.equals(BigDecimal.ZERO) ? "" : total.toString();
    }
}
