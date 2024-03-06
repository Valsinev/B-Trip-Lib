package org.example.engine;

import org.example.configuration.*;

import java.awt.image.BufferedImage;
import java.util.List;

public class TripTypeSelector {
    private IOrderTextCoordinates orderCoordinates;
    private final IOrderAdditionalDaysCoordinates orderAdditionalDaysCoordinates;
    private final ITravelListTextCoordinates travelListTextCoordinates;

    public TripTypeSelector(IOrderTextCoordinates orderCoordinates, IOrderAdditionalDaysCoordinates orderAdditionalDaysCoordinates, ITravelListTextCoordinates travelListTextCoordinates) {
        this.orderCoordinates = orderCoordinates;
        this.orderAdditionalDaysCoordinates = orderAdditionalDaysCoordinates;
        this.travelListTextCoordinates = travelListTextCoordinates;
    }

    public void select(BusinessTripForm form, List<BufferedImage> sheetStorage, IConfiguration configuration) {

        OrderListDataManager orderDataManager = new OrderListDataManager(form, sheetStorage, configuration, orderCoordinates, orderAdditionalDaysCoordinates);
        TravelListDataManager travelDataManager = new TravelListDataManager(form, sheetStorage, configuration, travelListTextCoordinates);


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
