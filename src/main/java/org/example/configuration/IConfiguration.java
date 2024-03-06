package org.example.configuration;

import java.awt.*;
import java.util.Optional;

public interface IConfiguration {
    Font getFont();

    Color getFontColor();

    long getDailyMoneyWithoutHotel();

    long getDailyMoneyWithHotel();

    int getNumberOfDaysInOneOrder();

    Optional<String> orderImgResourcePath();

    Optional<String> additionalDaysImgResourcePath();

    Optional<String> travelImgResourcePath();
}
