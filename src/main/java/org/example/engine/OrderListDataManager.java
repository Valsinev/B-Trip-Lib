package org.example.engine;

import org.example.constants.*;
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
import java.util.Objects;

public class OrderListDataManager {


    private final BusinessTripForm form;
    private final List<BufferedImage> sheetStorage;
    private final IConfiguration config;

    BufferedImage blankImage;
    BufferedImage blankAdditionalOrderDaysImage;


    public OrderListDataManager(BusinessTripForm form, List<BufferedImage> sheetStorage, IConfiguration config) {


        this.form = form;
        this.sheetStorage = sheetStorage;
        this.config = config;

        // Using try-with-resources to automatically close the input stream
        try (
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream("img/order.png");
                InputStream inputAddOrderDaysStream = getClass().getClassLoader().getResourceAsStream("img/order-days.png")
        ) {
            if (inputStream != null) {
                blankImage = ImageIO.read(inputStream);
                blankAdditionalOrderDaysImage = ImageIO.read(inputAddOrderDaysStream);
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


    public void getTripWithoutVehicleWithoutHotel() {
        DataManager dataManager = new DataManager(form, commonOrderData());
        dataManager.dataAdder(OrderTextCoordinates.dailyMoneyCoordinates, String.valueOf(config.getDailyMoneyWithoutHotel()));

        sheetStorage.add(ImageDrawer.drawDataOnBackgroundImg(dataManager.data, blankImage, config.getFont(), config.getFontColor()));
    }

    public void getTripWithOtherTransportWithoutHotel() {
        DataManager dataManager = new DataManager(form, commonOrderData());
        dataManager.dataAdder(OrderTextCoordinates.dailyMoneyCoordinates, String.valueOf(config.getDailyMoneyWithoutHotel()));

        numberOfOrdersCreation(form.getDays(), dataManager.data);
    }

    public void getTripWithOtherTransportWithHotel() {
        DataManager dataManager = new DataManager(form, commonOrderData());

        //if there is last day 20
        if (form.getIsTravelOnLastDay()) {
            dataManager.dataAdder(OrderTextCoordinates.dailyMoneyCoordinates, "    /" + config.getDailyMoneyWithoutHotel());
        }
        dataManager.dataAdder(OrderTextCoordinates.dailyMoneyCoordinates, String.valueOf(config.getDailyMoneyWithHotel()));

        if (form.getNightStayPrice().compareTo(BigDecimal.ZERO) > 0) {
            dataManager.dataAdder(OrderTextCoordinates.nightStayMoneyCoordinates, String.format("%.2f", form.getNightStayPrice()));
        }

        sheetStorage.add(ImageDrawer.drawDataOnBackgroundImg(dataManager.data, blankImage, config.getFont(), config.getFontColor()));
    }


    public void getTripWithoutVehicleWithHotel() {
        DataManager dataManager = new DataManager(form, commonOrderData());
        //if there is last day 20
        if (form.getIsTravelOnLastDay()) {
            dataManager.dataAdder(OrderTextCoordinates.dailyMoneyCoordinates, "    /" + config.getDailyMoneyWithoutHotel());
        }

        dataManager.dataAdder(OrderTextCoordinates.dailyMoneyCoordinates, String.valueOf(config.getDailyMoneyWithHotel()));

        dataManager.dataAdder(OrderTextCoordinates.nightStayMoneyCoordinates, String.format("%.2f", form.getNightStayPrice()));


        sheetStorage.add(ImageDrawer.drawDataOnBackgroundImg(dataManager.data, blankImage, config.getFont(), config.getFontColor()));
    }



    public void getTripWithVehicleWithoutHotel() {
        //populate dataManager with common travel data
        DataManager orderDataManager = new DataManager(form, commonOrderDataWithVehicle());

        orderDataManager.dataAdder(OrderTextCoordinates.dailyMoneyCoordinates, String.valueOf(config.getDailyMoneyWithoutHotel()));

        numberOfOrdersCreation(form.getDays(), orderDataManager.data);
    }


    public void getTripWithVehicleWithHotel() {

        //populate dataManager with common travel data
        DataManager dataManager = new DataManager(form, commonOrderDataWithVehicle());

        dataManager.dataAdder(OrderTextCoordinates.dailyMoneyCoordinates, String.valueOf(config.getDailyMoneyWithHotel()));

        dataManager.dataAdder(OrderTextCoordinates.nightStayMoneyCoordinates, String.format("%.2f", form.getNightStayPrice()));

        if (form.getIsTravelOnLastDay()) {
            dataManager.dataAdder(OrderTextCoordinates.departedDateCoordinates.get(0), String.format("%02d.%02d.%d", form.getDays().get(form.getDays().size() - 1), Integer.parseInt(form.getMonthNumber()), Integer.parseInt(form.getWhatYear())));
            dataManager.dataAdder(OrderTextCoordinates.dailyMoneyCoordinates,"    /" + config.getDailyMoneyWithoutHotel());
        }
        if (form.getIsTravelOnFirstDay()) {
            dataManager.dataAdder(OrderTextCoordinates.arrivedDateCoordinates.get(0), String.format("%02d.%02d.%d", form.getDays().get(0), Integer.parseInt(form.getMonthNumber()), Integer.parseInt(form.getWhatYear())));
            dataManager.dataAdder(OrderTextCoordinates.destinationCoordinates.get(0), form.getEndDestination());

        }
        sheetStorage.add(ImageDrawer.drawDataOnBackgroundImg(dataManager.data, blankImage, config.getFont(), config.getFontColor()));
    }

    //add all shared vehicle data in order list
    private List<ImgData> commonOrderDataWithVehicle() {
        DataManager dataManager = new DataManager(form, commonOrderData());
        dataManager.dataAdder(OrderTextCoordinates.vehicleMakeCoordinates, form.getMake());
        dataManager.dataAdder(OrderTextCoordinates.vehicleRegNumberCoordinates, form.getRegistrationNumber());
        dataManager.dataAdder(OrderTextCoordinates.fuelTypeCoordinates, form.getFuelType());
        dataManager.dataAdder(OrderTextCoordinates.typeMPSCoordinates, form.getCategory());
        BigDecimal totalFuelExpenses = ExpenseCalculator.calculateTotalFuelPrice(form.getKilometers(), form.getCostBy100(), form.getFuelPrice());

        //do not populate if fuelPrice, kilometers, costBy100, kilometers/100, total sum are 0
        if (totalFuelExpenses.compareTo(BigDecimal.ZERO) > 0) {
            dataManager.dataAdder(OrderTextCoordinates.fuelConsumptionFor100Coordinates, String.valueOf(form.getCostBy100()));
            dataManager.dataAdder(OrderTextCoordinates.fuelPriceCoordinates, String.format("%.2f", form.getFuelPrice()));
            dataManager.dataAdder(OrderTextCoordinates.kilometersCoordinates, form.getKilometers().toString());
            dataManager.dataAdder(OrderTextCoordinates.kilometersDividedBy100Coordinates, String.format("%.2f", form.getKilometers().divide(BigDecimal.valueOf(100.0), 2, RoundingMode.FLOOR)));
            dataManager.dataAdder(OrderTextCoordinates.totalSumForTransportCoordinates, String.format("%.2f", totalFuelExpenses.setScale(2, RoundingMode.FLOOR)));
            BigDecimal fuelConsumed = ExpenseCalculator.calculateFuelConsumed(form.getKilometers(), form.getCostBy100());
            dataManager.dataAdder(OrderTextCoordinates.totalFuelConsumedCoordinates, String.valueOf(fuelConsumed.setScale(2, RoundingMode.FLOOR)));
        }
        ////
        return dataManager.data;
    }

    //add all shared data in order list
    private List<ImgData> commonOrderData() {
        DataManager dataManager = new DataManager(form, new ArrayList<>());

        dataManager.dataAdder(OrderTextCoordinates.fullNameCoordinates, form.getFullName());
        dataManager.dataAdder(OrderTextCoordinates.fullNameAndEmployeePositionCoordinates, String.format("%s, %s",
                (form.getFullName().trim().isEmpty() ? "                                          ": form.getFullName()),
                form.getPosition()));
        dataManager.dataAdder(OrderTextCoordinates.numberDocumentsCoordinates, String.valueOf(form.getNumberDocuments() == 0 ? "" : form.getNumberDocuments()));
        dataManager.dataAdder(OrderTextCoordinates.startCityCoordinates, form.getStartDestination());
        dataManager.dataAdder(OrderTextCoordinates.endCityCoordinates, form.getEndDestination());
        dataManager.dataAdder(OrderTextCoordinates.numberOfDaysCoordinates, String.valueOf(form.getNumberOfDays()));
        dataManager.dataAdder(OrderTextCoordinates.startDateCoordinates, String.format("%02d.%02d.%d", form.getDays().get(0), Integer.parseInt(form.getMonthNumber()), Integer.parseInt(form.getWhatYear())));
        dataManager.dataAdder(OrderTextCoordinates.endDateCoordinates, String.format("%02d.%02d.%d", form.getDays().get(form.getDays().size() - 1), Integer.parseInt(form.getMonthNumber()), Integer.parseInt(form.getWhatYear())));
        dataManager.dataAdder(OrderTextCoordinates.reasonCoordinates, form.getReason());
        dataManager.dataAdder(OrderTextCoordinates.employerNameCoordinates, form.getHeadEmployeeName());
        String ifEmptyPersonalNumber = form.getPersonalNumber().trim().isEmpty() || form.getPersonalNumber() == null ? "              " : form.getPersonalNumber();
        dataManager.dataAdder(OrderTextCoordinates.orderNumberCoordinates, String.format("%s-%02d-%02d", ifEmptyPersonalNumber, Integer.parseInt(form.getMonthNumber()), Integer.parseInt(form.getTripNumberThisMonth())));
        dataManager.dataAdder(OrderTextCoordinates.businessTripFNumberCoordinates, form.getBranchIn());
        dataManager.dataAdder(OrderTextCoordinates.difficultiesCoordinates, TextConstants.DOESNT_ENCOUNTER);

        return dataManager.data;
    }


    //when You travel everyday you must set arrived and travel off date and end city destination for each day
    //the problem is that order list has limited slots for dates so this method creates number of orders and set their data + data for
    //every travel day and destination
    private void numberOfOrdersCreation(List<Integer> days, List<ImgData> data) {
        int daysInOrder = config.getNumberOfDaysInOneOrder();
        int ordersNeeded = days.size() > daysInOrder ? 2 : 1;

        if (ordersNeeded == 2) {
            // Create the first sublist from index 0 to 7 to have the days from 1 to 8
            List<Integer> orderDaysSublist = days.subList(0, 8);
            drawDatesAndDestination(
                    orderDaysSublist,
                    data,
                    blankImage,
                    OrderTextCoordinates.arrivedDateCoordinates,
                    OrderTextCoordinates.departedDateCoordinates,
                    OrderTextCoordinates.destinationCoordinates);

            // Create the second sublist from index 8 to 30
            List<Integer> addDaysSublist = days.subList(8, days.size());
            //create empty data manager
            DataManager addDaysManager = new DataManager(form, new ArrayList<>());
            //pass the sublist with dates with the coordinates of the additional arrived dates, departed dates, destination
            addDaysManager.dataAdder(
                    OrderAdditionalDaysCoordinates.endDateCoordinates, String.format("%02d.%02d.%d", addDaysSublist.get(addDaysSublist.size() - 1), Integer.parseInt(form.getMonthNumber()), Integer.parseInt(form.getWhatYear())));

            drawDatesAndDestination(
                    addDaysSublist,
                    addDaysManager.data,
                    blankAdditionalOrderDaysImage,
                    OrderAdditionalDaysCoordinates.arrivedDateCoordinates,
                    OrderAdditionalDaysCoordinates.departedDateCoordinates,
                    OrderAdditionalDaysCoordinates.destinationCoordinates);
        } else {
            drawDatesAndDestination(
                    days,
                    data,
                    blankImage,
                    OrderTextCoordinates.arrivedDateCoordinates,
                    OrderTextCoordinates.departedDateCoordinates,
                    OrderTextCoordinates.destinationCoordinates);
        }
    }

    private void drawDatesAndDestination(List<Integer> days, List<ImgData> data, BufferedImage blankImage, List<TextCords> arrivedDateCoordinates, List<TextCords> departedDateCoordinates, List<TextCords> destinationCoordinates) {
        //create data manager with current populated data
        DataManager currentManager = new DataManager(form, data);
        //pass the coordinates for arrived dates, departed dates, destination, the list with days and the manager to populate the manager data with dates and destination
        addDaysAndDestination(
                days,
                currentManager,
                arrivedDateCoordinates,
                departedDateCoordinates,
                destinationCoordinates);
        //draw all the data to the image
        BufferedImage currentImage = ImageDrawer.drawDataOnBackgroundImg(currentManager.data, blankImage, config.getFont(), config.getFontColor());
        //save the image in the collection of images
        sheetStorage.add(currentImage);
    }

    private void addDaysAndDestination(List<Integer> days, DataManager data, List<TextCords> arrivedDateCoordinates, List<TextCords> departedDateCoordinates, List<TextCords> destinationCoordinates) {
        ArrayList<Integer> daysToArray = new ArrayList<>(days);
        for (int i = 0; i < days.size(); i++) {
            data.dataAdder(arrivedDateCoordinates.get(i), String.format("%02d.%02d.%d", daysToArray.get(i), Integer.parseInt(form.getMonthNumber()), Integer.parseInt(form.getWhatYear())));
            data.dataAdder(departedDateCoordinates.get(i), String.format("%02d.%02d.%d", daysToArray.get(i), Integer.parseInt(form.getMonthNumber()), Integer.parseInt(form.getWhatYear())));
            data.dataAdder(destinationCoordinates.get(i), form.getEndDestination());
        }
    }
}
