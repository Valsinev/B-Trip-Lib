package org.example.configuration;

import org.example.engine.TextCords;

import java.util.List;

public interface IOrderAdditionalDaysCoordinates {
    List<TextCords> destinationCoordinates();

    List<TextCords> arrivedDateCoordinates();

    List<TextCords> departedDateCoordinates();
    List<TextCords> endDateCoordinates();
}
