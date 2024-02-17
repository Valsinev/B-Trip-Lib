package org.example.utillity;

import org.example.constants.Config;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class ExpenseCalculator {

    public static BigDecimal calculateTotalFuelPrice(BigDecimal kilometers, BigDecimal costBy100, BigDecimal fuelPrice) {
        BigDecimal distance = Objects.requireNonNullElse(kilometers, BigDecimal.ZERO);
        distance = BigDecValidator.asignZeroIfLessThanOne(distance);
        BigDecimal costFor100 = Objects.requireNonNullElse(costBy100, BigDecimal.ZERO);
        costFor100 = BigDecValidator.asignZeroIfLessThanOne(costFor100);
        BigDecimal fuelCost = Objects.requireNonNullElse(fuelPrice, BigDecimal.ZERO);
        fuelCost = BigDecValidator.asignZeroIfLessThanOne(fuelCost);
        return calculateFuelConsumed(distance, costFor100).multiply(fuelCost);
    }

    public static BigDecimal calculateFuelConsumed(BigDecimal kilometers, BigDecimal costBy100) {
        BigDecimal distance = Objects.requireNonNullElse(kilometers, BigDecimal.ZERO);
        distance = BigDecValidator.asignZeroIfLessThanOne(distance);
        BigDecimal costFor100 = Objects.requireNonNullElse(costBy100, BigDecimal.ZERO);
        costFor100 = BigDecValidator.asignZeroIfLessThanOne(costFor100);
        return distance.divide(BigDecimal.valueOf(100.0), 2, RoundingMode.FLOOR).multiply(costFor100);
    }

    public static BigDecimal calculateTotalExpenses(BigDecimal hotelExpenses, BigDecimal dailyExpenses, BigDecimal totalFuelExpenses, BigDecimal addExpenses) {

        BigDecimal hotel = Objects.requireNonNullElse(hotelExpenses, BigDecimal.ZERO);
        BigDecimal daily = Objects.requireNonNullElse(dailyExpenses, BigDecimal.ZERO);
        BigDecimal fuel = Objects.requireNonNullElse(totalFuelExpenses, BigDecimal.ZERO);
        BigDecimal add = Objects.requireNonNullElse(addExpenses, BigDecimal.ZERO);
        hotel = BigDecValidator.asignZeroIfLessThanOne(hotel);
        fuel = BigDecValidator.asignZeroIfLessThanOne(fuel);
        daily = BigDecValidator.asignZeroIfLessThanOne(daily);
        add = BigDecValidator.asignZeroIfLessThanOne(add);

            return hotel.add(daily).add(fuel).add(add);
    }

    public static BigDecimal calculateTotalExpenses(BigDecimal hotelExpenses, BigDecimal dailyExpenses, BigDecimal totalFuelExpenses, BigDecimal addExpenses, BigDecimal otherTransportExpenses) {

        BigDecimal hotel = Objects.requireNonNullElse(hotelExpenses, BigDecimal.ZERO);
        BigDecimal daily = Objects.requireNonNullElse(dailyExpenses, BigDecimal.ZERO);
        BigDecimal fuel = Objects.requireNonNullElse(totalFuelExpenses, BigDecimal.ZERO);
        BigDecimal add = Objects.requireNonNullElse(addExpenses, BigDecimal.ZERO);
        BigDecimal other = Objects.requireNonNullElse(otherTransportExpenses, BigDecimal.ZERO);
        hotel = BigDecValidator.asignZeroIfLessThanOne(hotel);
        fuel = BigDecValidator.asignZeroIfLessThanOne(fuel);
        daily = BigDecValidator.asignZeroIfLessThanOne(daily);
        add = BigDecValidator.asignZeroIfLessThanOne(add);
        other = BigDecValidator.asignZeroIfLessThanOne(other);

        return hotel.add(daily).add(fuel).add(add).add(other);
    }

    public static BigDecimal calcDailyMoney(BigDecimal numberOfDays, BigDecimal daysInHotel) {
        //nights in hotel * daily money for nightstay + (total days - nights in hotel = nights without hotel * daily money without night stay)

        BigDecimal result;
        BigDecimal hotel = Objects.requireNonNullElse(daysInHotel, BigDecimal.ZERO);
        BigDecimal days = Objects.requireNonNullElse(numberOfDays, BigDecimal.ZERO);
        hotel = BigDecValidator.asignZeroIfLessThanOne(hotel);
        days = BigDecValidator.asignZeroIfLessThanOne(days);

        if (BigDecValidator.isFirstSmallerThanSecond(days, hotel)) {
            result = BigDecimal.ZERO;
        } else {
            BigDecimal dailyMoneyWithNightStay = hotel.multiply(BigDecimal.valueOf(Config.DAILY_WITH_NIGHT_STAY));
            BigDecimal dailyMoneyWithoutNightStay = (days.subtract(hotel)).multiply(BigDecimal.valueOf(Config.DAILY_WITHOUT_NIGHT_STAY));
            dailyMoneyWithoutNightStay = BigDecValidator.asignZeroIfLessThanOne(dailyMoneyWithoutNightStay);
            result = dailyMoneyWithNightStay.add(dailyMoneyWithoutNightStay);
        }

        return result;
    }

    public static BigDecimal calculateTotalNightStayExpense(BigDecimal numberOfNightsStayed, BigDecimal nightStayPrice) {
        BigDecimal nights = Objects.requireNonNullElse(numberOfNightsStayed, BigDecimal.ZERO);
        BigDecimal cost = Objects.requireNonNullElse(nightStayPrice, BigDecimal.ZERO);
        nights = BigDecValidator.asignZeroIfLessThanOne(nights);
        cost = BigDecValidator.asignZeroIfLessThanOne(cost);

        return nights.multiply(cost);
    }
}
