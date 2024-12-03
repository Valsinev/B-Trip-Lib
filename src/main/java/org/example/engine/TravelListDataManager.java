package org.example.engine;

import org.example.configuration.BusinessTripForm;
import org.example.configuration.IConfiguration;
import org.example.configuration.ITravelListTextCoordinates;
import org.example.utillity.ExpenseCalculator;
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
    private final IConfiguration config;
    private final ITravelListTextCoordinates coordinates;
    private BufferedImage blankImage;

    public TravelListDataManager(BusinessTripForm form, List<BufferedImage> sheetStorage, IConfiguration config, ITravelListTextCoordinates coordinates) {

        this.form = form;
        this.sheetStorage = sheetStorage;
        this.config = config;
        this.coordinates = coordinates;

        // Using try-with-resources to automatically close the input stream
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(config.travelImgResourcePath().get())) {
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

        BigDecimal totalFuelExpenses = ExpenseCalculator.calculateTotalFuelPrice(form.getKilometers(), form.getCostBy100(), form.getFuelPrice());
        //if total fuel expenses are 0 don't generate travel list
        if (totalFuelExpenses.compareTo(BigDecimal.ZERO) > 0) {
            DataManager dataManager = new DataManager(form, commonTravelData());
            dateReasonKilometersAdderWithoutNightStay(dataManager);

            sheetStorage.add(ImageDrawer.drawDataOnBackgroundImg(dataManager.data, blankImage, config.getFont(), config.getFontColor()));
        }
    }

    public void getTravelWithHotel() {

        BigDecimal totalFuelExpenses = ExpenseCalculator.calculateTotalFuelPrice(form.getKilometers(), form.getCostBy100(), form.getFuelPrice());
        //if total fuel expenses are 0 don't generate travel list
        if (totalFuelExpenses.compareTo(BigDecimal.ZERO) > 0) {
            DataManager dataManager = new DataManager(form, commonTravelData());
            dateReasonKilometersAdderWithNightStay(dataManager);

            sheetStorage.add(ImageDrawer.drawDataOnBackgroundImg(dataManager.data, blankImage, config.getFont(), config.getFontColor()));
        }
    }

    private List<ImgData> commonTravelData() {
        DataManager dataManager = new DataManager(form, new ArrayList<>());
        dataManager.dataAdder(coordinates.fullNameCoordinates(), form.getFullName());
        dataManager.dataAdder(coordinates.personalNumberCoordinates(), form.getPersonalNumber());
        dataManager.dataAdder(coordinates.vehicleMakeAndModelCoordinates(),  String.format("%s %s", form.getMake(), form.getModel()));
        dataManager.dataAdder(coordinates.fuelConsumptionFor100Coordinates(), String.format("%.2f", form.getCostBy100()));
        dataManager.dataAdder(coordinates.vehicleRegNumberCoordinates(), form.getRegistrationNumber());
        dataManager.dataAdder(coordinates.fuelTypeCoordinates(), form.getFuelType());
        dataManager.dataAdder(coordinates.kilometersCoordinates(), form.getKilometers().toString());
        if (!"управител".equals(form.getHeadEmployeeName())) {
            dataManager.dataAdder(coordinates.employerNameCoordinates(), form.getHeadEmployeeName());
        }
        dataManager.dataAdder(coordinates.monthAndYearCoordinates(), String.format("%s/%s", form.getMonthNumber(), form.getWhatYear()));
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
                        coordinates.destinationAndReasonCoordinates().get(day),
                        String.format("%s-%s-%s/%s/",
                                form.getStartDestination(),
                                form.getEndDestination(),
                                form.getStartDestination(),
                                form.getReason()));
            }
            //dates
            dataManager.dataAdder(
                    coordinates.days().get(day),
                    String.format("%02d.%02d.%d",
                            form.getDays().get(day),
                            Integer.parseInt(form.getMonthNumber()),
                            Integer.parseInt(form.getWhatYear())));
            //kilometers
            dataManager.dataAdder(coordinates.kilometersForEachDayCoordinates().get(day), String.valueOf(kilometers));
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
                dataManager.dataAdder(coordinates.destinationAndReasonCoordinates().get(0), String.format("%s-%s/%s/", form.getStartDestination(), form.getEndDestination(), form.getReason()));
                //end city-start city for last day
                dataManager.dataAdder(coordinates.destinationAndReasonWithNightStayCoordinates().get(1), String.format("%s-%s", form.getEndDestination(), form.getStartDestination()));
            }
            //first date
            dataManager.dataAdder(coordinates.days().get(0), String.format("%02d.%02d.%d", form.getDays().get(0), Integer.parseInt(form.getMonthNumber()), Integer.parseInt(form.getWhatYear())));
            //last date
            dataManager.dataAdder(coordinates.days().get(1), String.format("%02d.%02d.%d", form.getDays().get(form.getDays().size() - 1), Integer.parseInt(form.getMonthNumber()), Integer.parseInt(form.getWhatYear())));

            BigDecimal kilometers = form.getKilometers().divide( BigDecimal.valueOf(2), 0, RoundingMode.FLOOR);
            //half of the kilometers
            dataManager.dataAdder(coordinates.kilometersForEachDayWithNightStayCoordinates().get(0), String.valueOf(kilometers));
            //other half of the kilometers
            dataManager.dataAdder(coordinates.kilometersForEachDayWithNightStayCoordinates().get(1), String.valueOf(kilometers));
        }
        if (form.getIsTravelOnLastDay() && !form.getIsTravelOnFirstDay()) {
            //end city-start city
            if (form.getStartDestination() != null && !form.getStartDestination().trim().isEmpty() &&
                    form.getEndDestination() != null && !form.getEndDestination().trim().isEmpty()
            ) {
                dataManager.dataAdder(coordinates.destinationAndReasonWithNightStayCoordinates().get(0), String.format("%s-%s", form.getEndDestination(), form.getStartDestination()));
            }
            //last date
            dataManager.dataAdder(coordinates.days().get(0), String.format("%02d.%02d.%d", form.getDays().get(form.getDays().size() - 1), Integer.parseInt(form.getMonthNumber()), Integer.parseInt(form.getWhatYear())));
            //all kilometers
            dataManager.dataAdder(coordinates.kilometersForEachDayWithNightStayCoordinates().get(0), String.valueOf(form.getKilometers()));
        }
        if (form.getIsTravelOnFirstDay() && !form.getIsTravelOnLastDay()) {
            //start city-end city-start city/reason/
            if (form.getStartDestination() != null && !form.getStartDestination().trim().isEmpty() &&
                    form.getEndDestination() != null && !form.getEndDestination().trim().isEmpty() &&
                    form.getReason() != null && !form.getReason().trim().isEmpty()
            ) {
                dataManager.dataAdder(coordinates.destinationAndReasonCoordinates().get(0), String.format("%s-%s/%s/", form.getStartDestination(), form.getEndDestination(), form.getReason()));
            }
            //first date
            dataManager.dataAdder(coordinates.days().get(0), String.format("%02d.%02d.%d", form.getDays().get(0), Integer.parseInt(form.getMonthNumber()), Integer.parseInt(form.getWhatYear())));
            //all kilometers
            dataManager.dataAdder(coordinates.kilometersForEachDayWithNightStayCoordinates().get(0), String.valueOf(form.getKilometers()));
        }
    }
}
