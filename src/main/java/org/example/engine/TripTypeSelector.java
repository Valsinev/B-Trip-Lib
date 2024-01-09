package org.example.engine;

import java.awt.image.BufferedImage;
import java.util.List;

public class TripTypeSelector {
    public static void select(BusinessTripForm form, List<BufferedImage> sheetStorage) {

        OrderListDataManager orderDataManager = new OrderListDataManager(form, sheetStorage);
        TravelListDataManager travelDataManager = new TravelListDataManager(form, sheetStorage);

        if (form.getIsTravelWithYourVehicle()) {
            if (!form.getIsNightStayedInHotel()) {
                orderDataManager.getTripWithVehicleWithoutHotel();
                travelDataManager.getTravelWithoutHotel();
            } else {
                orderDataManager.getTripWithVehicleWithHotel();
                travelDataManager.getTravelWithHotel();
            }
        } else {
            if (!form.getIsNightStayedInHotel()) {
                orderDataManager.getTripWithoutVehicleWithoutHotel();
            } else {
                orderDataManager.getTripWithoutVehicleWithHotel();
            }
        }
    }
}
