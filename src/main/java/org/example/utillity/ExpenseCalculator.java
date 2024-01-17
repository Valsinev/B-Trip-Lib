package org.example.utillity;

import org.example.constants.Config;

import java.math.BigDecimal;

public class ExpenseCalculator {
    public static String calculateTotalFuelPrice(String kilometers, String costBy100, String fuelPrice) {
        if (!FieldValidator.validateDigitField(kilometers) || !FieldValidator.validateDigitField(costBy100) || !FieldValidator.validateDigitField(fuelPrice)) {
            return "0.0";
        }
        return String.format("%.2f", new BigDecimal(calculateFuelConsumed(kilometers, costBy100)).multiply(new BigDecimal(fuelPrice)));
    }

    public static String calculateFuelConsumed(String kilometers, String costBy100) {
        if (!FieldValidator.validateDigitField(kilometers) || !FieldValidator.validateDigitField(costBy100)) {
            return "0.0";
        }
        return new BigDecimal(kilometers).divide(BigDecimal.valueOf(100.0)).multiply(new BigDecimal(costBy100)).toString();
    }

    public static String calculateTotalExpenses(String hotelExpenses, String dailyExpenses, String totalFuelExpenses, String addExpenses) {

        BigDecimal hotel = FieldValidator.validateDigitField(hotelExpenses) ? new BigDecimal(hotelExpenses) : BigDecimal.ZERO;
        BigDecimal daily = FieldValidator.validateDigitField(dailyExpenses) ? new BigDecimal(dailyExpenses) : BigDecimal.ZERO;
        BigDecimal fuel = FieldValidator.validateDigitField(totalFuelExpenses) ? new BigDecimal(totalFuelExpenses) : BigDecimal.ZERO;
        BigDecimal add = FieldValidator.validateDigitField(addExpenses) ? new BigDecimal(addExpenses) : BigDecimal.ZERO;
            return String.format("%.2f",
                    hotel
                    .add(daily)
                    .add(fuel)
                    .add(add));

    }

    public static String calcDailyMoney(String numberOfDays, String daysInHotel) {
        //nights in hotel * daily money for nightstay + (total days - nights in hotel = nights without hotel * daily money without night stay)
        String result = "0.0";
        String nightsInHotel = daysInHotel;
        if (numberOfDays == null || !FieldValidator.validateDigitField(numberOfDays)) {
            return result;
        }
        if (daysInHotel == null || !FieldValidator.validateDigitField(daysInHotel)) {
            nightsInHotel = "0";
        }
        BigDecimal dailyMoneyWithNightStay = new BigDecimal(nightsInHotel).multiply(BigDecimal.valueOf(Config.DAILY_WITH_NIGHT_STAY));
        BigDecimal dailyMoneyWithoutNightStay = (new BigDecimal(numberOfDays).subtract(new BigDecimal(nightsInHotel))).multiply(BigDecimal.valueOf(Config.DAILY_WITHOUT_NIGHT_STAY));
        return dailyMoneyWithNightStay.add(dailyMoneyWithoutNightStay).toString();
    }

    public static String calculateTotalNightStayExpense(String numberOfNightsStayed, String nightStayPrice) {
        if (numberOfNightsStayed == null || nightStayPrice == null ||
                !FieldValidator.validateDigitField(numberOfNightsStayed) || !FieldValidator.validateDigitField(nightStayPrice)) {
            return "0";
        }
        BigDecimal total = new BigDecimal(numberOfNightsStayed).multiply(new BigDecimal(nightStayPrice));
        return total.toString();
    }
}
