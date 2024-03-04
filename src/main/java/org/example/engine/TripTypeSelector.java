package org.example.engine;

import org.example.constants.IConfiguration;

import java.awt.image.BufferedImage;
import java.util.List;

public class TripTypeSelector {
    public static void select(BusinessTripForm form, List<BufferedImage> sheetStorage, IConfiguration configuration) {

        OrderListDataManager orderDataManager = new OrderListDataManager(form, sheetStorage, configuration);
        TravelListDataManager travelDataManager = new TravelListDataManager(form, sheetStorage, configuration);


        boolean isTravelWithOtherTransport = form.getIsTravelWithOtherTransport();
        boolean isWithHotelStay = form.getIsNightStayedInHotel();
        boolean isTravelWithYourVehicle = form.getIsTravelWithYourVehicle();


        //if stayed in hotel (no transport to destination every day)
        if (isWithHotelStay) {
            if (isTravelWithYourVehicle) {
                orderDataManager.getTripWithVehicleWithHotel();
                travelDataManager.getTravelWithHotel();
            } else if (isTravelWithOtherTransport) {
                orderDataManager.getTripWithOtherTransportWithHotel();
            } else {
                orderDataManager.getTripWithoutVehicleWithHotel();
            }
        }
        //if travel to the destination every day (without nightstay)
        else {
            if (isTravelWithYourVehicle) {
                orderDataManager.getTripWithVehicleWithoutHotel();
                travelDataManager.getTravelWithoutHotel();
            } else if (isTravelWithOtherTransport) {
                orderDataManager.getTripWithOtherTransportWithoutHotel();
            } else {
                orderDataManager.getTripWithoutVehicleWithoutHotel();
            }
        }
    }
}
