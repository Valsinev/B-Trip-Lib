package org.example.engine;

import org.example.constants.TravelListTextCoordinates;
import org.example.utillity.ImageDrawer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TravelListDataManager {
    private final BusinessTripForm form;
    private final List<BufferedImage> sheetStorage;
    private final BufferedImage blankImage;

    public TravelListDataManager(BusinessTripForm form, List<BufferedImage> sheetStorage) {

        this.form = form;
        this.sheetStorage = sheetStorage;


        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("img/travel.png");
        System.out.println(inputStream);
        try {
            assert inputStream != null;
            blankImage = ImageIO.read(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getTravelWithoutHotel() {
        DataManager dataManager = new DataManager(form, commonTravelData());
        dateReasonKilometersAdderWithoutNightStay(dataManager);

        sheetStorage.add(ImageDrawer.drawDataOnBackgroundImg(dataManager.data, blankImage));
    }

    public void getTravelWithHotel() {
        DataManager dataManager = new DataManager(form, commonTravelData());
        dateReasonKilometersAdderWithNightStay(dataManager);

        sheetStorage.add(ImageDrawer.drawDataOnBackgroundImg(dataManager.data, blankImage));
    }

    private List<ImgData> commonTravelData() {
        DataManager dataManager = new DataManager(form, new ArrayList<>());

        dataManager.dataAdder(TravelListTextCoordinates.fullNameCoordinates, form.getFullName());
        dataManager.dataAdder(TravelListTextCoordinates.personalNumberCoordinates, form.getPersonalNumber());
        dataManager.dataAdder(TravelListTextCoordinates.vehicleMakeAndModelCoordinates, form.getMakeAndModel());
        dataManager.dataAdder(TravelListTextCoordinates.fuelConsumptionFor100Coordinates, form.getCostBy100());
        dataManager.dataAdder(TravelListTextCoordinates.vehicleRegNumberCoordinates, form.getRegistrationNumber());
        dataManager.dataAdder(TravelListTextCoordinates.fuelTypeCoordinates, form.getFuelType());
        dataManager.dataAdder(TravelListTextCoordinates.kilometersCoordinates, form.getKilometers());
        dataManager.dataAdder(TravelListTextCoordinates.employerNameCoordinates, form.getHeadEmployeeName());
        dataManager.dataAdder(TravelListTextCoordinates.monthAndYearCoordinates, String.format("%s/%s", form.getMonthNumber(), form.getWhatYear()));
        return dataManager.data;
    }



    //loops each day add dates, reason in startCity-endCity-startCity/reason/ format and kilometers on correct coordinates
    private void dateReasonKilometersAdderWithoutNightStay(DataManager dataManager) {
        int kilometers = Integer.parseInt(form.getKilometers()) / form.getNumberOfDays();
        for (int day = 0; day < form.getDays().size(); day++) {
            //start city-end city-start city/reason/
            dataManager.dataAdder(TravelListTextCoordinates.destinationAndReasonCoordinates.get(day), String.format("%s-%s-%s/%s/", form.getStartDestination(), form.getEndDestination(), form.getStartDestination(), form.getReason()));
            //dates
            dataManager.dataAdder(TravelListTextCoordinates.days.get(day), String.format("%02d.%02d.%d", form.getDays().get(day), Integer.parseInt(form.getMonthNumber()), Integer.parseInt(form.getWhatYear())));
            //kilometers
            dataManager.dataAdder(TravelListTextCoordinates.kilometersForEachDayCoordinates.get(day), String.valueOf(kilometers));
        }
    }


    //this method formats the dates, destination, reason, kilometers and adds it to the list
    //if only travel on the first day format is startDestination-endDestination/reason/
    //if only travel on the last day format is endDestination-startDestination
    //if travel on the first and the last day format is startDestination-endDestination/reason/ second row endDestination-startDestination
    private void dateReasonKilometersAdderWithNightStay(DataManager dataManager) {
        if (form.getIsTravelOnFirstDay() && form.getIsTravelOnLastDay()) {
            //start city-end city/reason/ for first day
            dataManager.dataAdder(TravelListTextCoordinates.destinationAndReasonCoordinates.get(0), String.format("%s-%s/%s/", form.getStartDestination(), form.getEndDestination(), form.getReason()));
            //end city-start city for last day
            dataManager.dataAdder(TravelListTextCoordinates.destinationAndReasonWithNightStayCoordinates.get(1), String.format("%s-%s", form.getEndDestination(), form.getStartDestination()));

            //first date
            dataManager.dataAdder(TravelListTextCoordinates.days.get(0), String.format("%02d.%02d.%d", form.getDays().get(0), Integer.parseInt(form.getMonthNumber()), Integer.parseInt(form.getWhatYear())));
            //last date
            dataManager.dataAdder(TravelListTextCoordinates.days.get(1), String.format("%02d.%02d.%d", form.getDays().get(form.getDays().size() - 1), Integer.parseInt(form.getMonthNumber()), Integer.parseInt(form.getWhatYear())));

            int kilometers = Integer.parseInt(form.getKilometers()) / 2;
            //half of the kilometers
            dataManager.dataAdder(TravelListTextCoordinates.kilometersForEachDayWithNightStayCoordinates.get(0), String.valueOf(kilometers));
            //other half of the kilometers
            dataManager.dataAdder(TravelListTextCoordinates.kilometersForEachDayWithNightStayCoordinates.get(1), String.valueOf(kilometers));
        }
        if (form.getIsTravelOnLastDay() && !form.getIsTravelOnFirstDay()) {
            //end city-start city
            dataManager.dataAdder(TravelListTextCoordinates.destinationAndReasonWithNightStayCoordinates.get(0), String.format("%s-%s", form.getEndDestination(), form.getStartDestination()));
            //last date
            dataManager.dataAdder(TravelListTextCoordinates.days.get(0), String.format("%02d.%02d.%d", form.getDays().get(form.getDays().size() - 1), Integer.parseInt(form.getMonthNumber()), Integer.parseInt(form.getWhatYear())));
            //all kilometers
            dataManager.dataAdder(TravelListTextCoordinates.kilometersForEachDayWithNightStayCoordinates.get(0), form.getKilometers());
        }
        if (form.getIsTravelOnFirstDay() && !form.getIsTravelOnLastDay()) {
            //start city-end city-start city/reason/
            dataManager.dataAdder(TravelListTextCoordinates.destinationAndReasonCoordinates.get(0), String.format("%s-%s/%s/", form.getStartDestination(), form.getEndDestination(), form.getReason()));
            //first date
            dataManager.dataAdder(TravelListTextCoordinates.days.get(0), String.format("%02d.%02d.%d", form.getDays().get(0), Integer.parseInt(form.getMonthNumber()), Integer.parseInt(form.getWhatYear())));
            //all kilometers
            dataManager.dataAdder(TravelListTextCoordinates.kilometersForEachDayWithNightStayCoordinates.get(0), form.getKilometers());
        }
    }
}
