package org.example.engine;

import org.example.configuration.IOrderTextCoordinates;

import java.util.List;

public class OrderTextCoordinates implements IOrderTextCoordinates {

    @Override
    public List<TextCords> fullNameCoordinates() {
        return List.of(
                new TextCords(340, 240));
    }

    @Override
    public List<TextCords> dailyMoneyCoordinates() {
        return List.of(
                new TextCords(1600, 735));
    }

    @Override
    public List<TextCords> numberOfDaysCoordinates() {
        return List.of(
                new TextCords(1470, 560)
        );
    }

    @Override
    public List<TextCords> dailyMoneyTotalSumCoordinates() {
        return List.of(
                new TextCords(140, 740)
        );
    }

    @Override
    public List<TextCords> totalSumCoordinates() {
        return List.of(
                new TextCords(140, 970)
        );
    }

    @Override
    public List<TextCords> startDateCoordinates() {
        return List.of(
                new TextCords(1990, 246),
                new TextCords(1350, 1390),
                new TextCords(1655, 560)
        );
    }

    @Override
    public List<TextCords> endDateCoordinates() {
        return List.of(
                new TextCords(210, 1320),
                new TextCords(210, 3310),
                new TextCords(1980, 556),
                new TextCords(1350, 2880),
                new TextCords(1350, 3310)
        );
    }

    @Override
    public List<TextCords> orderNumberCoordinates() {
        return List.of(
                new TextCords(1660, 246)
        );
    }

    @Override
    public List<TextCords> fullNameAndEmployeePositionCoordinates() {
        return List.of(
                new TextCords(1670, 416),
                new TextCords(1670, 1678)
        );
    }

    @Override
    public List<TextCords> startCityCoordinates() {
        return List.of(
                new TextCords(1470, 511)
        );
    }

    @Override
    public List<TextCords> endCityCoordinates() {
        return List.of(
                new TextCords(1470, 467)
        );
    }

    @Override
    public List<TextCords> reasonCoordinates() {
        return List.of(
                new TextCords(1670, 1888),
                new TextCords(1470, 604),
                new TextCords(1670, 2100)
        );
    }

    @Override
    public List<TextCords> typeMPSCoordinates() {
        return List.of(
                new TextCords(1460, 927)
        );
    }

    @Override
    public List<TextCords> vehicleMakeCoordinates() {
        return List.of(
                new TextCords(1700, 927)
        );
    }

    @Override
    public List<TextCords> vehicleRegNumberCoordinates() {
        return List.of(
                new TextCords(2020, 927)
        );
    }

    @Override
    public List<TextCords> fuelTypeCoordinates() {
        return List.of(
                new TextCords(1400, 966)
        );
    }

    @Override
    public List<TextCords> fuelConsumptionFor100Coordinates() {
        return List.of(
                new TextCords(390, 455),
                new TextCords(1860, 965)
        );
    }

    @Override
    public List<TextCords> fuelPriceCoordinates() {
        return List.of(
                new TextCords(800, 455),
                new TextCords(2200, 965)
        );
    }

    @Override
    public List<TextCords> kilometersCoordinates() {
        return List.of(
                new TextCords(466, 416)
        );
    }

    @Override
    public List<TextCords> kilometersDividedBy100Coordinates() {
        return List.of(
                new TextCords(250, 455)
        );
    }

    @Override
    public List<TextCords> totalFuelConsumedCoordinates() {
        return List.of(
                new TextCords(630, 455)
        );
    }

    @Override
    public List<TextCords> otherTransportCoordinates() {
        return List.of(
                new TextCords(140, 365)
        );
    }

    @Override
    public List<TextCords> totalSumForTransportCoordinates() {
        return List.of(
                new TextCords(140, 513)
        );
    }

    @Override
    public List<TextCords> employerNameCoordinates() {
        return List.of(
                new TextCords(1500, 1810)
        );
    }

    @Override
    public List<TextCords> businessTripFNumberCoordinates() {
        return List.of(
                new TextCords(1500, 1960)
        );
    }

    @Override
    public List<TextCords> difficultiesCoordinates() {
        return List.of(
                new TextCords(1300, 2530)
        );
    }

    @Override
    public List<TextCords> nightStayMoneyCoordinates() {
        return List.of(
                new TextCords(1600, 790)
        );
    }

    @Override
    public List<TextCords> numberDocumentsCoordinates() {
        return List.of(
                new TextCords(377, 1015)
        );
    }

    @Override
    public List<TextCords> totalNightStayMoneyCoordinates() {
        return List.of(
                new TextCords(140, 835)
        );
    }

    @Override
    public List<TextCords> arrivedDateCoordinates() {
        return List.of(
                new TextCords(240, 1640),  //day1
                new TextCords(240, 1886),  //day2
                new TextCords(240, 2100),  //day3
                new TextCords(240, 2314),  //day4
                new TextCords(240, 2530),  //day5
                new TextCords(240, 2740),  //day6
                new TextCords(240, 2953),  //day7
                new TextCords(240, 3168)   //day8
        );
    }

    @Override
    public List<TextCords> departedDateCoordinates() {
        return List.of(
                new TextCords(740, 1528),  //day1
                new TextCords(740, 1814),  //day2
                new TextCords(740, 2030),  //day3
                new TextCords(740, 2243),  //day4
                new TextCords(740, 2454),  //day5
                new TextCords(740, 2670),  //day6
                new TextCords(740, 2880),  //day7
                new TextCords(740, 3095)   //day8
        );
    }

    @Override
    public List<TextCords> destinationCoordinates() {
        return List.of(
                new TextCords(300, 1528),  //day1
                new TextCords(300, 1814),  //day2
                new TextCords(300, 2030),  //day3
                new TextCords(300, 2243),  //day4
                new TextCords(300, 2454),  //day5
                new TextCords(300, 2670),  //day6
                new TextCords(300, 2880),  //day7
                new TextCords(300, 3095)   //day8
        );
    }
}
