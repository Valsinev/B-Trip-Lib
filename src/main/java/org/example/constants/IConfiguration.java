package org.example.constants;

import java.awt.*;

public interface IConfiguration {
    Font getFont();
    Color getFontColor();
    long getDailyMoneyWithoutHotel();
    long getDailyMoneyWithHotel();
    int getNumberOfDaysInOneOrder();
}
