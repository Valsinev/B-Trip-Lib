package org.example.utillity;

import org.example.configuration.BusinessTripForm;

import java.util.ArrayList;
import java.util.List;

public class BTripGetDaysFromCheckboxesOrFields {

    public static List<Integer> getDays(BusinessTripForm bTrip) {
        List<Integer> days = new ArrayList<>();
        List<Boolean> daysCheckBox = List.of(false,
                bTrip.getDay1(), bTrip.getDay2(), bTrip.getDay3(), bTrip.getDay4(), bTrip.getDay5(), bTrip.getDay6(), bTrip.getDay7(), bTrip.getDay8(),
                bTrip.getDay9(), bTrip.getDay10(), bTrip.getDay11(), bTrip.getDay12(), bTrip.getDay13(), bTrip.getDay14(), bTrip.getDay15(), bTrip.getDay16(),
                bTrip.getDay17(), bTrip.getDay18(), bTrip.getDay19(), bTrip.getDay20(), bTrip.getDay21(), bTrip.getDay22(), bTrip.getDay23(), bTrip.getDay24(),
                bTrip.getDay25(), bTrip.getDay26(), bTrip.getDay27(), bTrip.getDay28(), bTrip.getDay29(), bTrip.getDay30(), bTrip.getDay31());


        if (!bTrip.getIsNightStayedInHotel()) {
            daysAdder(daysCheckBox, days);
        }
        if (bTrip.getIsNightStayedInHotel()) {
            for (int i = bTrip.getFromWhichDayField(); i <= bTrip.getToWhichDayField(); i++) {
                days.add(i);
            }
        }
        return days;
    }



    private static void daysAdder(List<Boolean> daysCheckBoxes, List<Integer> days) {
        for (int i = 1; i <= 31 ; i++) {
            if (daysCheckBoxes.get(i)) {
                days.add(i);
            }
        }
    }
}
