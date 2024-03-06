package org.example.configuration;

import org.example.engine.TextCords;

import java.util.List;

public interface IOrderTextCoordinates {
    List<TextCords> fullNameCoordinates();
    List<TextCords> dailyMoneyCoordinates();
    List<TextCords> numberOfDaysCoordinates();

    @Deprecated
    List<TextCords> dailyMoneyTotalSumCoordinates();
    @Deprecated
    List<TextCords> totalSumCoordinates();
    List<TextCords> startDateCoordinates();
    List<TextCords> endDateCoordinates();
    List<TextCords> orderNumberCoordinates();
    List<TextCords> fullNameAndEmployeePositionCoordinates();
    List<TextCords> startCityCoordinates();
    List<TextCords> endCityCoordinates();
    List<TextCords> reasonCoordinates();
    List<TextCords> typeMPSCoordinates();
    List<TextCords> vehicleMakeCoordinates();
    List<TextCords> vehicleRegNumberCoordinates();
    List<TextCords> fuelTypeCoordinates();
    List<TextCords> fuelConsumptionFor100Coordinates();
    List<TextCords> fuelPriceCoordinates();
    List<TextCords> kilometersCoordinates();
    List<TextCords> kilometersDividedBy100Coordinates();
    List<TextCords> totalFuelConsumedCoordinates();
    @Deprecated
    List<TextCords> otherTransportCoordinates();
    List<TextCords> totalSumForTransportCoordinates();
    List<TextCords> employerNameCoordinates();
    List<TextCords> businessTripFNumberCoordinates();
    List<TextCords> difficultiesCoordinates();
   List<TextCords> nightStayMoneyCoordinates();
    List<TextCords> numberDocumentsCoordinates();
    @Deprecated
    List<TextCords> totalNightStayMoneyCoordinates();
    List<TextCords> arrivedDateCoordinates();
    List<TextCords> departedDateCoordinates();
    List<TextCords> destinationCoordinates();
}
