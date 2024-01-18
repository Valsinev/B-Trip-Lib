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
import java.util.ArrayList;
import java.util.List;

public class OrderListDataManager {


    private final BusinessTripForm form;
    private final List<BufferedImage> sheetStorage;

    InputStream inputStream;
    BufferedImage blankImage;


    public OrderListDataManager(BusinessTripForm form, List<BufferedImage> sheetStorage) {

        this.form = form;
        this.sheetStorage = sheetStorage;

        inputStream = getClass().getClassLoader().getResourceAsStream("img/order.png");

        try {
            assert inputStream != null;
            blankImage = ImageIO.read(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void getTripWithoutVehicleWithoutHotel() {
        DataManager dataManager = new DataManager(form, commonOrderData());
        dataManager.dataAdder(OrderTextCoordinates.dailyMoneyCoordinates, String.valueOf(Config.DAILY_WITHOUT_NIGHT_STAY));
        String dailyExpenses = ExpenseCalculator.calcDailyMoney(String.valueOf(form.getNumberOfDays()), form.getNumberOfNightsStayed());
        String hotelExpenses = "0";
        String fuelExpenses = "0";
        String total = ExpenseCalculator.calculateTotalExpenses(hotelExpenses, dailyExpenses, fuelExpenses, form.getAdditionalExpenses());
        dataManager.dataAdder(OrderTextCoordinates.totalSumCoordinates, total);

        sheetStorage.add(ImageDrawer.drawDataOnBackgroundImg(dataManager.data, blankImage, Config.FONT, Config.FONT_COLOR));
    }

    public void getTripWithoutVehicleWithHotel() {
        DataManager dataManager = new DataManager(form, commonOrderData());
        //if there is last day 20
        if (form.getIsTravelOnLastDay()) {
            dataManager.dataAdder(OrderTextCoordinates.dailyMoneyCoordinates, "    /" +Config.DAILY_WITHOUT_NIGHT_STAY);
        }

        dataManager.dataAdder(OrderTextCoordinates.dailyMoneyCoordinates, String.valueOf(Config.DAILY_WITH_NIGHT_STAY));
        String dailyExpenses = ExpenseCalculator.calcDailyMoney(String.valueOf(form.getNumberOfDays()), form.getNumberOfNightsStayed());
        String hotelExpenses = ExpenseCalculator.calculateTotalNightStayExpense(form.getNumberOfNightsStayed(), form.getNightStayPrice());
        String fuelExpenses = "0";
        String total = ExpenseCalculator.calculateTotalExpenses(hotelExpenses, dailyExpenses, fuelExpenses, form.getAdditionalExpenses());
        dataManager.dataAdder(OrderTextCoordinates.totalSumCoordinates, total);
        dataManager.dataAdder(OrderTextCoordinates.totalNightStayMoneyCoordinates, hotelExpenses);
        dataManager.dataAdder(OrderTextCoordinates.nightStayMoneyCoordinates, form.getNightStayPrice());

        sheetStorage.add(ImageDrawer.drawDataOnBackgroundImg(dataManager.data, blankImage, Config.FONT, Config.FONT_COLOR));
    }



    public void getTripWithVehicleWithoutHotel() {
        DataManager orderDataManager = new DataManager(form, commonOrderDataWithVehicle());

        orderDataManager.dataAdder(OrderTextCoordinates.dailyMoneyCoordinates, String.valueOf(Config.DAILY_WITHOUT_NIGHT_STAY));
        String daysInHotel = "0";
        String dailyTotal = ExpenseCalculator.calcDailyMoney(String.valueOf(form.getNumberOfDays()), daysInHotel);
        String fuelTotal = ExpenseCalculator.calculateTotalFuelPrice(form.getKilometers(), form.getCostBy100(), form.getFuelPrice());
        String hotelExpenses = "0";
        orderDataManager.dataAdder(OrderTextCoordinates.totalSumCoordinates, ExpenseCalculator.calculateTotalExpenses(hotelExpenses, dailyTotal, fuelTotal, form.getAdditionalExpenses()));

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

        String dailyTotal = ExpenseCalculator.calcDailyMoney(String.valueOf(form.getNumberOfDays()), form.getNumberOfNightsStayed());
        String fuelTotal = ExpenseCalculator.calculateTotalFuelPrice(form.getKilometers(), form.getCostBy100(), form.getFuelPrice());
        String nightStayTotal = ExpenseCalculator.calculateTotalNightStayExpense(form.getNumberOfNightsStayed(), form.getNightStayPrice());

        dataManager.dataAdder(OrderTextCoordinates.totalSumCoordinates, ExpenseCalculator.calculateTotalExpenses(nightStayTotal, dailyTotal, fuelTotal, form.getAdditionalExpenses()));
        dataManager.dataAdder(OrderTextCoordinates.dailyMoneyCoordinates, String.valueOf(Config.DAILY_WITH_NIGHT_STAY));
        if (form.getIsTravelOnLastDay()) {
            dataManager.dataAdder(OrderTextCoordinates.dailyMoneyCoordinates,"    /" + Config.DAILY_WITHOUT_NIGHT_STAY);
        }
        dataManager.dataAdder(OrderTextCoordinates.nightStayMoneyCoordinates, form.getNightStayPrice().equals("0") ? "" : form.getNightStayPrice());
        dataManager.dataAdder(OrderTextCoordinates.totalNightStayMoneyCoordinates, nightStayTotal);

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
        dataManager.dataAdder(OrderTextCoordinates.vehicleMakeAndModelCoordinates, form.getMakeAndModel());
        dataManager.dataAdder(OrderTextCoordinates.vehicleRegNumberCoordinates, form.getRegistrationNumber());
        dataManager.dataAdder(OrderTextCoordinates.fuelConsumptionFor100Coordinates, String.valueOf(form.getCostBy100()));
        dataManager.dataAdder(OrderTextCoordinates.fuelTypeCoordinates, form.getFuelType());
        dataManager.dataAdder(OrderTextCoordinates.fuelPriceCoordinates, form.getFuelPrice());
        dataManager.dataAdder(OrderTextCoordinates.kilometersCoordinates, form.getKilometers());
        dataManager.dataAdder(OrderTextCoordinates.kilometersDividedBy100Coordinates, String.valueOf(new BigDecimal(form.getKilometers()).divide(BigDecimal.valueOf(100.0))));
        dataManager.dataAdder(OrderTextCoordinates.typeMPSCoordinates, form.getCategory());
        dataManager.dataAdder(OrderTextCoordinates.totalSumForTransportCoordinates, ExpenseCalculator.calculateTotalFuelPrice(form.getKilometers(), form.getCostBy100(), form.getFuelPrice()));
        dataManager.dataAdder(OrderTextCoordinates.totalFuelConsumedCoordinates, String.valueOf(ExpenseCalculator.calculateFuelConsumed(form.getKilometers(), form.getCostBy100())));
        return dataManager.data;
    }

    //add all shared data in order list
    private List<ImgData> commonOrderData() {
        DataManager dataManager = new DataManager(form, new ArrayList<>());

        dataManager.dataAdder(OrderTextCoordinates.fullNameCoordinates, form.getFullName());
        dataManager.dataAdder(OrderTextCoordinates.fullNameAndEmployeePositionCoordinates, String.format("%s, %s", form.getFullName(), form.getPosition()));
        dataManager.dataAdder(OrderTextCoordinates.numberDocumentsCoordinates, String.valueOf(form.getNumberDocuments()));
        dataManager.dataAdder(OrderTextCoordinates.startCityCoordinates, form.getStartDestination());
        dataManager.dataAdder(OrderTextCoordinates.endCityCoordinates, form.getEndDestination());
        dataManager.dataAdder(OrderTextCoordinates.numberOfDaysCoordinates, String.valueOf(form.getNumberOfDays()));
        dataManager.dataAdder(OrderTextCoordinates.startDateCoordinates, String.format("%02d.%02d.%d", form.getDays().get(0), Integer.parseInt(form.getMonthNumber()), Integer.parseInt(form.getWhatYear())));
        dataManager.dataAdder(OrderTextCoordinates.endDateCoordinates, String.format("%02d.%02d.%d", form.getDays().get(form.getDays().size() - 1), Integer.parseInt(form.getMonthNumber()), Integer.parseInt(form.getWhatYear())));
        dataManager.dataAdder(OrderTextCoordinates.reasonCoordinates, form.getReason());
        dataManager.dataAdder(OrderTextCoordinates.employerNameCoordinates, form.getHeadEmployeeName());
        dataManager.dataAdder(OrderTextCoordinates.orderNumberCoordinates, String.format("%s-%02d-%02d", form.getPersonalNumber(), Integer.parseInt(form.getMonthNumber()), Integer.parseInt(form.getTripNumberThisMonth())));
        dataManager.dataAdder(OrderTextCoordinates.businessTripFNumberCoordinates, form.getBranchIn());
        dataManager.dataAdder(OrderTextCoordinates.difficultiesCoordinates, TextConstants.DOESNT_ENCOUNTER);
        dataManager.dataAdder(OrderTextCoordinates.dailyMoneyTotalSumCoordinates, ExpenseCalculator.calcDailyMoney(String.valueOf(form.getNumberOfDays()), form.getNumberOfNightsStayed()));

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
