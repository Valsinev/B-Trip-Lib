package org.example.engine;


import org.example.configuration.BusinessTripForm;
import org.example.configuration.IConfiguration;
import org.example.utillity.ExpenseCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BusinessTripTest {

    public static void main(String[] args) {
        // Create a mock object for the interface
        BusinessTripForm form = mock(BusinessTripForm.class);
        IConfiguration configuration = mock(IConfiguration.class);

//        withoutVehicleWithoutHotel(form, configuration);
        withVehicleWithoutHotel(form, configuration);
//        withVehicleWithHotel(form, configuration);

        List<BufferedImage> images = new ArrayList<>();

        TripTypeSelector selector = new TripTypeSelector(new OrderTextCoordinates(), new OrderAdditionalDaysCoordinates(), new TravelListTextCoordinates());

        selector.select(form, images, configuration);


        SwingUtilities.invokeLater(() -> new ImageDisplay(images));
        // Use the mock object
    }

    private static void withVehicleWithHotel(BusinessTripForm form, IConfiguration configuration) {

        // Define behavior for the mock object
        when(form.getFullName()).thenReturn("Venisllav Ivanov Stoyanov");
        when(form.getPersonalNumber()).thenReturn("6208");
        when(form.getPosition()).thenReturn("KP");
        when(form.getStartDestination()).thenReturn("atija");
        when(form.getEndDestination()).thenReturn("carevo");
        when(form.getBranchIn()).thenReturn("202");
        when(form.getNumberOfDays()).thenReturn(BigDecimal.valueOf(10));
        when(form.getDays()).thenReturn(List.of(1,2,3,4,5,6,7,8,9,10));
        when(form.getMonthNumber()).thenReturn("1");
        when(form.getWhatYear()).thenReturn("2024");
        when(form.getHeadEmployeeName()).thenReturn("Hristina Petkova");
        when(form.getReason()).thenReturn("podpomagane F202");
        when(form.getTripNumberThisMonth()).thenReturn(String.valueOf(1));
        when(form.getAdditionalExpenses()).thenReturn(BigDecimal.ZERO);
        when(form.getNumberDocuments()).thenReturn(1);
        when(form.getIsTravelWithYourVehicle()).thenReturn(true);
        when(form.getIsTravelWithOtherTransport()).thenReturn(false);
        when(form.getIsNightStayedInHotel()).thenReturn(true);

        when(configuration.getDailyMoneyWithHotel()).thenReturn(40L);
        when(configuration.getDailyMoneyWithoutHotel()).thenReturn(20L);
        when(configuration.getFont()).thenReturn(new Font("Arial", Font.ITALIC, 25));
        when(configuration.getNumberOfDaysInOneOrder()).thenReturn(8);
        when(configuration.getFontColor()).thenReturn(Color.RED);
        when(configuration.orderImgResourcePath()).thenReturn(Optional.of("img/order.png"));
        when(configuration.additionalDaysImgResourcePath()).thenReturn(Optional.of("img/order-days.png"));
        when(configuration.travelImgResourcePath()).thenReturn(Optional.of("img/travel.png"));
        when(configuration.getScale()).thenReturn(2);
        when(configuration.getRoundingMode()).thenReturn(RoundingMode.HALF_UP);

        when(form.getMake()).thenReturn("opel");
        when(form.getModel()).thenReturn("grandland");
        when(form.getCategory()).thenReturn("lek");
        when(form.getRegistrationNumber()).thenReturn("A1334NM");
        when(form.getCostBy100()).thenReturn(BigDecimal.valueOf(10));
        when(form.getFuelType()).thenReturn("benzin");
        when(form.getFuelPrice()).thenReturn(BigDecimal.valueOf(2.61));
        when(form.getKilometers()).thenReturn(BigDecimal.valueOf(660));

        when(form.getNightStayPrice()).thenReturn(BigDecimal.valueOf(50));
        when(form.getNumberOfNightsStayed()).thenReturn(BigDecimal.valueOf(9));
//        when(form.getFromWhichDayField()).thenReturn(1);
//        when(form.getToWhichDayField()).thenReturn(10);
        when(form.getIsTravelOnFirstDay()).thenReturn(true);
        when(form.getIsTravelOnLastDay()).thenReturn(true);
    }

    private static void withVehicleWithoutHotel(BusinessTripForm form, IConfiguration configuration) {

        // Define behavior for the mock object
        when(form.getFullName()).thenReturn("Venisllav Ivanov Stoyanov");
        when(form.getPersonalNumber()).thenReturn("6208");
        when(form.getPosition()).thenReturn("KP");
        when(form.getStartDestination()).thenReturn("atija");
        when(form.getEndDestination()).thenReturn("carevo");
        when(form.getBranchIn()).thenReturn("202");
        when(form.getNumberOfDays()).thenReturn(BigDecimal.valueOf(10));
        when(form.getDays()).thenReturn(List.of(1,2,3,4,5,6,7,8,9,10));
        when(form.getMonthNumber()).thenReturn("1");
        when(form.getWhatYear()).thenReturn("2024");
        when(form.getHeadEmployeeName()).thenReturn("Hristina Petkova");
        when(form.getReason()).thenReturn("podpomagane F202");
        when(form.getTripNumberThisMonth()).thenReturn(String.valueOf(1));
        when(form.getAdditionalExpenses()).thenReturn(BigDecimal.ZERO);
        when(form.getNumberDocuments()).thenReturn(1);
        when(form.getIsTravelWithYourVehicle()).thenReturn(true);
        when(form.getIsTravelWithOtherTransport()).thenReturn(false);
        when(form.getIsNightStayedInHotel()).thenReturn(false);

        when(configuration.getDailyMoneyWithoutHotel()).thenReturn(20L);
        when(configuration.getFont()).thenReturn(new Font("Arial", Font.ITALIC, 25));
        when(configuration.getNumberOfDaysInOneOrder()).thenReturn(8);
        when(configuration.getFontColor()).thenReturn(Color.RED);
        when(configuration.orderImgResourcePath()).thenReturn(Optional.of("img/order.png"));
        when(configuration.additionalDaysImgResourcePath()).thenReturn(Optional.of("img/order-days.png"));
        when(configuration.travelImgResourcePath()).thenReturn(Optional.of("img/travel.png"));
        when(configuration.getScale()).thenReturn(2);
        when(configuration.getRoundingMode()).thenReturn(RoundingMode.HALF_UP);


        when(form.getIsTravelWithYourVehicle()).thenReturn(true);
        when(form.getMake()).thenReturn("opel");
        when(form.getModel()).thenReturn("grandland");
        when(form.getCategory()).thenReturn("lek");
        when(form.getRegistrationNumber()).thenReturn("A1334NM");
        when(form.getCostBy100()).thenReturn(BigDecimal.valueOf(10));
        when(form.getFuelType()).thenReturn("benzin");
        when(form.getFuelPrice()).thenReturn(BigDecimal.valueOf(2.61));
        when(form.getKilometers()).thenReturn(BigDecimal.valueOf(660));
    }

    private static void withoutVehicleWithoutHotel(BusinessTripForm form, IConfiguration configuration) {
        // Define behavior for the mock object
        when(form.getFullName()).thenReturn("Venisllav Ivanov Stoyanov");
        when(form.getPersonalNumber()).thenReturn("6208");
        when(form.getPosition()).thenReturn("KP");
        when(form.getStartDestination()).thenReturn("atija");
        when(form.getEndDestination()).thenReturn("carevo");

        when(form.getNumberOfDays()).thenReturn(BigDecimal.valueOf(10));
        when(form.getDays()).thenReturn(List.of(1,2,3,4,5,6,7,8,9,10));
        when(form.getMonthNumber()).thenReturn("1");
        when(form.getWhatYear()).thenReturn("2024");
        when(form.getTripNumberThisMonth()).thenReturn(String.valueOf(1));
        when(form.getAdditionalExpenses()).thenReturn(BigDecimal.ZERO);
        when(form.getNumberDocuments()).thenReturn(1);
        when(form.getIsTravelWithYourVehicle()).thenReturn(true);
        when(form.getIsTravelWithOtherTransport()).thenReturn(false);
        when(form.getIsNightStayedInHotel()).thenReturn(false);

        when(configuration.getDailyMoneyWithoutHotel()).thenReturn(20L);
        when(configuration.getFont()).thenReturn(new Font("Arial", Font.ITALIC, 25));
        when(configuration.getNumberOfDaysInOneOrder()).thenReturn(8);
        when(configuration.getFontColor()).thenReturn(Color.RED);
        when(configuration.orderImgResourcePath()).thenReturn(Optional.of("img/order.png"));
        when(configuration.additionalDaysImgResourcePath()).thenReturn(Optional.of("img/order-days.png"));
        when(configuration.travelImgResourcePath()).thenReturn(Optional.of("img/travel.png"));
        when(configuration.getScale()).thenReturn(2);
        when(configuration.getRoundingMode()).thenReturn(RoundingMode.HALF_UP);
    }

    public static class ImageDisplay extends JFrame {

        public ImageDisplay(List<BufferedImage> images) {
            setTitle("Image Display");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(0, 1));
            setSize(800, 600);

            JScrollPane scrollPane = new JScrollPane();
            getContentPane().add(scrollPane);

            JPanel panel = new JPanel(new GridLayout(0, 1));
            scrollPane.setViewportView(panel);

            for (BufferedImage image : images) {
                JLabel label = new JLabel(new ImageIcon(image));
                panel.add(label);
            }

            setVisible(true);
        }
    }
}
