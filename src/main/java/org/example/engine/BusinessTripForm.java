package org.example.engine;

import java.math.BigDecimal;
import java.util.List;

public interface BusinessTripForm {

    String getFullName();
    String getPersonalNumber();
    String getPosition();
    int getNumberDocuments();
    String getBranchIn();
    String getEndDestination();
    String getStartDestination();
    BigDecimal getNumberOfDays();
    String getMonthNumber();
    String getWhatYear();
    String getReason();
    String getHeadEmployeeName();
    String getTripNumberThisMonth();
    BigDecimal getAdditionalExpenses();
    boolean getIsNightStayedInHotel();
    BigDecimal getNightStayPrice();
    BigDecimal getNumberOfNightsStayed();
    boolean getIsTravelOnFirstDay();
    boolean getIsTravelOnLastDay();
    boolean getIsTravelWithYourVehicle();
    String getModel();
    String getMake();
    String getCategory();
    String getRegistrationNumber();
    BigDecimal getCostBy100();
    String getFuelType();
    BigDecimal getFuelPrice();
    BigDecimal getKilometers();

    int getFromWhichDayField();

    int getToWhichDayField();

    List<Integer> getDays();

    boolean getDay1();

    boolean getDay2();

    boolean getDay3();

    boolean getDay4();

    boolean getDay5();

    boolean getDay6();

    boolean getDay7();

    boolean getDay8();

    boolean getDay9();

    boolean getDay10();

    boolean getDay11();

    boolean getDay12();

    boolean getDay13();

    boolean getDay14();

    boolean getDay15();

    boolean getDay16();

    boolean getDay17();

    boolean getDay18();

    boolean getDay19();

    boolean getDay20();

    boolean getDay21();

    boolean getDay22();

    boolean getDay23();

    boolean getDay24();

    boolean getDay25();

    boolean getDay26();

    boolean getDay27();

    boolean getDay28();

    boolean getDay29();

    boolean getDay30();

    boolean getDay31();
}
