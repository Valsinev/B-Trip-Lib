package org.example.engine;

import org.example.constants.Config;
import org.example.constants.OrderTextCoordinates;
import org.example.constants.TextConstants;
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

    BufferedImage blankImage;


    public OrderListDataManager(BusinessTripForm form, List<BufferedImage> sheetStorage) {


        this.form = form;
        this.sheetStorage = sheetStorage;

        // Using try-with-resources to automatically close the input stream
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("img/order.png")) {
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


    public void getTripWithoutVehicleWithoutHotel() {
        DataManager dataManager = new DataManager(form, commonOrderData());
        dataManager.dataAdder(OrderTextCoordinates.dailyMoneyCoordinates, String.valueOf(Config.DAILY_WITHOUT_NIGHT_STAY));

        BigDecimal dailyExpenses = ExpenseCalculator.calcDailyMoney(form.getNumberOfDays(), form.getNumberOfNightsStayed());
        BigDecimal total = ExpenseCalculator.calculateTotalExpenses(BigDecimal.ZERO, dailyExpenses, BigDecimal.ZERO, form.getAdditionalExpenses());
        dataManager.dataAdder(OrderTextCoordinates.totalSumCoordinates, String.format("%.2f", total));

        sheetStorage.add(ImageDrawer.drawDataOnBackgroundImg(dataManager.data, blankImage, Config.FONT, Config.FONT_COLOR));
    }

    public void getTripWithoutVehicleWithHotel() {
        DataManager dataManager = new DataManager(form, commonOrderData());
        //if there is last day 20
        if (form.getIsTravelOnLastDay()) {
            dataManager.dataAdder(OrderTextCoordinates.dailyMoneyCoordinates, "    /" +Config.DAILY_WITHOUT_NIGHT_STAY);
        }

        dataManager.dataAdder(OrderTextCoordinates.dailyMoneyCoordinates, String.valueOf(Config.DAILY_WITH_NIGHT_STAY));

        BigDecimal dailyExpenses = ExpenseCalculator.calcDailyMoney(form.getNumberOfDays(), form.getNumberOfNightsStayed());
        BigDecimal hotelExpenses = ExpenseCalculator.calculateTotalNightStayExpense(form.getNumberOfNightsStayed(), form.getNightStayPrice());
        BigDecimal fuelExpenses = BigDecimal.ZERO;
        BigDecimal total = ExpenseCalculator.calculateTotalExpenses(hotelExpenses, dailyExpenses, fuelExpenses, form.getAdditionalExpenses());
        dataManager.dataAdder(OrderTextCoordinates.totalSumCoordinates, String.format("%.2f", total));
        dataManager.dataAdder(OrderTextCoordinates.totalNightStayMoneyCoordinates, String.format("%.2f", hotelExpenses));
        dataManager.dataAdder(OrderTextCoordinates.nightStayMoneyCoordinates, String.format("%.2f", form.getNightStayPrice()));

        sheetStorage.add(ImageDrawer.drawDataOnBackgroundImg(dataManager.data, blankImage, Config.FONT, Config.FONT_COLOR));
    }



    public void getTripWithVehicleWithoutHotel() {
        DataManager orderDataManager = new DataManager(form, commonOrderDataWithVehicle());

        orderDataManager.dataAdder(OrderTextCoordinates.dailyMoneyCoordinates, String.valueOf(Config.DAILY_WITHOUT_NIGHT_STAY));

        BigDecimal daysInHotel = BigDecimal.ZERO;
        BigDecimal dailyTotal = ExpenseCalculator.calcDailyMoney(form.getNumberOfDays(), daysInHotel);
        BigDecimal fuelTotal = ExpenseCalculator.calculateTotalFuelPrice(form.getKilometers(), form.getCostBy100(), form.getFuelPrice());
        BigDecimal hotelExpenses = BigDecimal.ZERO;
        orderDataManager.dataAdder(OrderTextCoordinates.totalSumCoordinates, String.format("%.2f", ExpenseCalculator.calculateTotalExpenses(hotelExpenses, dailyTotal, fuelTotal, form.getAdditionalExpenses())));


        numberOfOrdersCreation(form.getDays(), orderDataManager.data);
    }


    //when You travel everyday you must set arrived and travel off date and end city destination for each day
    //the problem is that order list has limited slots for dates so this method creates number of orders and set their data + data for
    //every travel day and destination
    private void numberOfOrdersCreation(List<Integer> days, List<ImgData> data) {
        int daysInOrder = Config.NUMBER_OF_DAYS_IN_ONE_ORDER;
        int ordersNeeded = days.size() / daysInOrder;
        if (days.size() % daysInOrder != 0) ordersNeeded++;

        for (int i = 0; i < ordersNeeded; i++) {

            List<ImgData> newData = new ArrayList<>(data);
            DataManager currentManager = new DataManager(form, newData);
            int max = (i + 1) * daysInOrder;
            List<Integer> currentCays = days.subList(i*daysInOrder, Math.min(max, days.size()));
            addDaysAndDestination(currentCays, currentManager);
            BufferedImage currentImage = ImageDrawer.drawDataOnBackgroundImg(currentManager.data, blankImage, Config.FONT, Config.FONT_COLOR);
            sheetStorage.add(currentImage);
        }
    }

    public void getTripWithVehicleWithHotel() {

        DataManager dataManager = new DataManager(form, commonOrderDataWithVehicle());

        BigDecimal dailyTotal = ExpenseCalculator.calcDailyMoney(form.getNumberOfDays(), form.getNumberOfNightsStayed());
        BigDecimal fuelTotal = ExpenseCalculator.calculateTotalFuelPrice(form.getKilometers(), form.getCostBy100(), form.getFuelPrice());
        BigDecimal nightStayTotal = ExpenseCalculator.calculateTotalNightStayExpense(form.getNumberOfNightsStayed(), form.getNightStayPrice());

        dataManager.dataAdder(OrderTextCoordinates.totalSumCoordinates, String.format("%.2f", ExpenseCalculator.calculateTotalExpenses(nightStayTotal, dailyTotal, fuelTotal, form.getAdditionalExpenses())));
        dataManager.dataAdder(OrderTextCoordinates.dailyMoneyCoordinates, String.valueOf(Config.DAILY_WITH_NIGHT_STAY));
        if (form.getIsTravelOnLastDay()) {
            dataManager.dataAdder(OrderTextCoordinates.dailyMoneyCoordinates,"    /" + Config.DAILY_WITHOUT_NIGHT_STAY);
        }
        if (!Objects.equals(form.getNightStayPrice(), BigDecimal.ZERO) && !Objects.equals(nightStayTotal, BigDecimal.ZERO)) {
            dataManager.dataAdder(OrderTextCoordinates.nightStayMoneyCoordinates, String.format("%.2f", form.getNightStayPrice()));
            dataManager.dataAdder(OrderTextCoordinates.totalNightStayMoneyCoordinates, String.format("%.2f", nightStayTotal));
        }

        if (form.getIsTravelOnLastDay()) {
            dataManager.dataAdder(OrderTextCoordinates.days.get(0), String.format("%02d.%02d.%d", form.getDays().get(0), Integer.parseInt(form.getMonthNumber()), Integer.parseInt(form.getWhatYear())));
            dataManager.dataAdder(OrderTextCoordinates.destinationCoordinates.get(0), form.getEndDestination());
        }
        if (form.getIsTravelOnFirstDay()) {
            dataManager.dataAdder(OrderTextCoordinates.days2.get(0), String.format("%02d.%02d.%d", form.getDays().get(form.getDays().size() - 1), Integer.parseInt(form.getMonthNumber()), Integer.parseInt(form.getWhatYear())));
        }
        sheetStorage.add(ImageDrawer.drawDataOnBackgroundImg(dataManager.data, blankImage, Config.FONT, Config.FONT_COLOR));
    }

    //add all shared vehicle data in order list
    private List<ImgData> commonOrderDataWithVehicle() {
        DataManager dataManager = new DataManager(form, commonOrderData());
        dataManager.dataAdder(OrderTextCoordinates.vehicleMakeCoordinates, form.getMake());
        dataManager.dataAdder(OrderTextCoordinates.vehicleRegNumberCoordinates, form.getRegistrationNumber());
        dataManager.dataAdder(OrderTextCoordinates.fuelConsumptionFor100Coordinates, String.valueOf(form.getCostBy100()));
        dataManager.dataAdder(OrderTextCoordinates.fuelTypeCoordinates, form.getFuelType());
        dataManager.dataAdder(OrderTextCoordinates.fuelPriceCoordinates, String.format("%.2f", form.getFuelPrice()));
        dataManager.dataAdder(OrderTextCoordinates.kilometersCoordinates, form.getKilometers().toString());
        dataManager.dataAdder(OrderTextCoordinates.kilometersDividedBy100Coordinates,  String.format("%.2f", form.getKilometers().divide(BigDecimal.valueOf(100.0), 2, RoundingMode.FLOOR)));
        dataManager.dataAdder(OrderTextCoordinates.typeMPSCoordinates, form.getCategory());
        dataManager.dataAdder(OrderTextCoordinates.totalSumForTransportCoordinates, String.format("%.2f", ExpenseCalculator.calculateTotalFuelPrice(form.getKilometers(), form.getCostBy100(), form.getFuelPrice())));
        dataManager.dataAdder(OrderTextCoordinates.totalFuelConsumedCoordinates, String.valueOf(ExpenseCalculator.calculateFuelConsumed(form.getKilometers(), form.getCostBy100())));
        return dataManager.data;
    }

    //add all shared data in order list
    private List<ImgData> commonOrderData() {
        DataManager dataManager = new DataManager(form, new ArrayList<>());

        dataManager.dataAdder(OrderTextCoordinates.fullNameCoordinates, form.getFullName());
        dataManager.dataAdder(OrderTextCoordinates.fullNameAndEmployeePositionCoordinates, String.format("%s, %s", form.getFullName(), form.getPosition()));
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

        dataManager.dataAdder(OrderTextCoordinates.dailyMoneyTotalSumCoordinates, String.format("%.2f", ExpenseCalculator.calcDailyMoney(form.getNumberOfDays(), form.getNumberOfNightsStayed())));


        return dataManager.data;
    }

    private void addDaysAndDestination(List<Integer> days, DataManager data) {
        ArrayList<Integer> daysToArray = new ArrayList<>(days);
        for (int i = 0; i < days.size(); i++) {
            data.dataAdder(OrderTextCoordinates.days.get(i), String.format("%02d.%02d.%d", daysToArray.get(i), Integer.parseInt(form.getMonthNumber()), Integer.parseInt(form.getWhatYear())));
            data.dataAdder(OrderTextCoordinates.days2.get(i), String.format("%02d.%02d.%d", daysToArray.get(i), Integer.parseInt(form.getMonthNumber()), Integer.parseInt(form.getWhatYear())));
            data.dataAdder(OrderTextCoordinates.destinationCoordinates.get(i), form.getEndDestination());
        }
    }
}
