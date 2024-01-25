package org.example.engine;

import org.example.constants.Config;
import org.example.constants.TravelListTextCoordinates;
import org.example.utillity.ImageDrawer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class TravelListDataManager {
    private final BusinessTripForm form;
    private final List<BufferedImage> sheetStorage;
    private BufferedImage blankImage;

    public TravelListDataManager(BusinessTripForm form, List<BufferedImage> sheetStorage) {

        this.form = form;
        this.sheetStorage = sheetStorage;

        // Using try-with-resources to automatically close the input stream
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("img/travel.png")) {
            if (inputStream != null) {
                blankImage = ImageIO.read(inputStream);
            } else {
                // Handle the case where the resource is not found
                throw new RuntimeException("Image resource not found");
            }
        } catch (IOException e) {
            e.printStackTrace(); // Log the exception or handle it appropriately
            // Optionally, you may choose to rethrow the exception or return a default image.
            // throw new RuntimeException("Failed to read image", e);
        }
    }

    public void getTravelWithoutHotel() {
        DataManager dataManager = new DataManager(form, commonTravelData());
        dateReasonKilometersAdderWithoutNightStay(dataManager);

        sheetStorage.add(ImageDrawer.drawDataOnBackgroundImg(dataManager.data, blankImage, Config.FONT, Config.FONT_COLOR));
    }

    public void getTravelWithHotel() {
        DataManager dataManager = new DataManager(form, commonTravelData());
        dateReasonKilometersAdderWithNightStay(dataManager);

        sheetStorage.add(ImageDrawer.drawDataOnBackgroundImg(dataManager.data, blankImage, Config.FONT, Config.FONT_COLOR));
    }

    private List<ImgData> commonTravelData() {
        DataManager dataManager = new DataManager(form, new ArrayList<>());

        dataManager.dataAdder(TravelListTextCoordinates.fullNameCoordinates, form.getFullName());
        dataManager.dataAdder(TravelListTextCoordinates.personalNumberCoordinates, form.getPersonalNumber());
        dataManager.dataAdder(TravelListTextCoordinates.vehicleMakeAndModelCoordinates,  String.format("%s %s", form.getMake(), form.getModel()));
        dataManager.dataAdder(TravelListTextCoordinates.fuelConsumptionFor100Coordinates, String.format("%.2f", form.getCostBy100()));
        dataManager.dataAdder(TravelListTextCoordinates.vehicleRegNumberCoordinates, form.getRegistrationNumber());
        dataManager.dataAdder(TravelListTextCoordinates.fuelTypeCoordinates, form.getFuelType());
        dataManager.dataAdder(TravelListTextCoordinates.kilometersCoordinates, form.getKilometers().toString());
        dataManager.dataAdder(TravelListTextCoordinates.employerNameCoordinates, form.getHeadEmployeeName());
        dataManager.dataAdder(TravelListTextCoordinates.monthAndYearCoordinates, String.format("%s/%s", form.getMonthNumber(), form.getWhatYear()));
        return dataManager.data;
    }



    //loops each day add dates, reason in startCity-endCity-startCity/reason/ format and kilometers on correct coordinates
    private void dateReasonKilometersAdderWithoutNightStay(DataManager dataManager) {

        BigDecimal kilometers = form.getKilometers().divide(form.getNumberOfDays(), 0, RoundingMode.FLOOR);

        for (int day = 0; day < form.getDays().size(); day++) {

            if (form.getStartDestination() != null && !form.getStartDestination().trim().isEmpty() &&
                    form.getEndDestination() != null && !form.getEndDestination().trim().isEmpty() &&
                    form.getReason() != null && !form.getReason().trim().isEmpty()
            ) {
                //start city-end city-start city/reason/
                dataManager.dataAdder(
                        TravelListTextCoordinates.destinationAndReasonCoordinates.get(day),
                        String.format("%s-%s-%s/%s/",
                                form.getStartDestination(),
                                form.getEndDestination(),
                                form.getStartDestination(),
                                form.getReason()));
            }
            //dates
            dataManager.dataAdder(
                    TravelListTextCoordinates.days.get(day),
                    String.format("%02d.%02d.%d",
                            form.getDays().get(day),
                            Integer.parseInt(form.getMonthNumber()),
                            Integer.parseInt(form.getWhatYear())));
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
            if (form.getStartDestination() != null && !form.getStartDestination().trim().isEmpty() &&
                    form.getEndDestination() != null && !form.getEndDestination().trim().isEmpty() &&
                    form.getReason() != null && !form.getReason().trim().isEmpty()
            ) {
                dataManager.dataAdder(TravelListTextCoordinates.destinationAndReasonCoordinates.get(0), String.format("%s-%s/%s/", form.getStartDestination(), form.getEndDestination(), form.getReason()));
                //end city-start city for last day
                dataManager.dataAdder(TravelListTextCoordinates.destinationAndReasonWithNightStayCoordinates.get(1), String.format("%s-%s", form.getEndDestination(), form.getStartDestination()));
            }
            //first date
            dataManager.dataAdder(TravelListTextCoordinates.days.get(0), String.format("%02d.%02d.%d", form.getDays().get(0), Integer.parseInt(form.getMonthNumber()), Integer.parseInt(form.getWhatYear())));
            //last date
            dataManager.dataAdder(TravelListTextCoordinates.days.get(1), String.format("%02d.%02d.%d", form.getDays().get(form.getDays().size() - 1), Integer.parseInt(form.getMonthNumber()), Integer.parseInt(form.getWhatYear())));

            BigDecimal kilometers = form.getKilometers().divide( BigDecimal.valueOf(2), 0, RoundingMode.FLOOR);
            //half of the kilometers
            dataManager.dataAdder(TravelListTextCoordinates.kilometersForEachDayWithNightStayCoordinates.get(0), String.valueOf(kilometers));
            //other half of the kilometers
            dataManager.dataAdder(TravelListTextCoordinates.kilometersForEachDayWithNightStayCoordinates.get(1), String.valueOf(kilometers));
        }
        if (form.getIsTravelOnLastDay() && !form.getIsTravelOnFirstDay()) {
            //end city-start city
            if (form.getStartDestination() != null && !form.getStartDestination().trim().isEmpty() &&
                    form.getEndDestination() != null && !form.getEndDestination().trim().isEmpty()
            ) {
                dataManager.dataAdder(TravelListTextCoordinates.destinationAndReasonWithNightStayCoordinates.get(0), String.format("%s-%s", form.getEndDestination(), form.getStartDestination()));
            }
            //last date
            dataManager.dataAdder(TravelListTextCoordinates.days.get(0), String.format("%02d.%02d.%d", form.getDays().get(form.getDays().size() - 1), Integer.parseInt(form.getMonthNumber()), Integer.parseInt(form.getWhatYear())));
            //all kilometers
            dataManager.dataAdder(TravelListTextCoordinates.kilometersForEachDayWithNightStayCoordinates.get(0), String.valueOf(form.getKilometers()));
        }
        if (form.getIsTravelOnFirstDay() && !form.getIsTravelOnLastDay()) {
            //start city-end city-start city/reason/
            if (form.getStartDestination() != null && !form.getStartDestination().trim().isEmpty() &&
                    form.getEndDestination() != null && !form.getEndDestination().trim().isEmpty() &&
                    form.getReason() != null && !form.getReason().trim().isEmpty()
            ) {
                dataManager.dataAdder(TravelListTextCoordinates.destinationAndReasonCoordinates.get(0), String.format("%s-%s/%s/", form.getStartDestination(), form.getEndDestination(), form.getReason()));
            }
            //first date
            dataManager.dataAdder(TravelListTextCoordinates.days.get(0), String.format("%02d.%02d.%d", form.getDays().get(0), Integer.parseInt(form.getMonthNumber()), Integer.parseInt(form.getWhatYear())));
            //all kilometers
            dataManager.dataAdder(TravelListTextCoordinates.kilometersForEachDayWithNightStayCoordinates.get(0), String.valueOf(form.getKilometers()));
        }
    }
}
