package org.example.configuration;

import org.example.engine.TextCords;

import java.util.List;

public interface ITravelListTextCoordinates {
    List<TextCords> fullNameCoordinates();
    List<TextCords> personalNumberCoordinates();
    List<TextCords> vehicleMakeAndModelCoordinates();
    List<TextCords> fuelConsumptionFor100Coordinates();
    List<TextCords> vehicleRegNumberCoordinates();
    List<TextCords> fuelTypeCoordinates();
    List<TextCords> kilometersCoordinates();
    List<TextCords> employerNameCoordinates();
    List<TextCords> monthAndYearCoordinates();
    List<TextCords> days();
    List<TextCords> destinationAndReasonCoordinates();
    List<TextCords> kilometersForEachDayCoordinates();
    List<TextCords> destinationAndReasonWithNightStayCoordinates();
    List<TextCords> kilometersForEachDayWithNightStayCoordinates();
}
