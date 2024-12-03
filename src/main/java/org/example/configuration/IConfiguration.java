package org.example.configuration;

import java.awt.*;
import java.math.RoundingMode;
import java.util.Optional;

public interface IConfiguration {
    Font getFont();

    Color getFontColor();

    long getDailyMoneyWithoutHotel();

    long getDailyMoneyWithHotel();

    int getNumberOfDaysInOneOrder();

    int getScale();

    RoundingMode getRoundingMode();

    Optional<String> orderImgResourcePath();

    Optional<String> additionalDaysImgResourcePath();

    Optional<String> travelImgResourcePath();
}
