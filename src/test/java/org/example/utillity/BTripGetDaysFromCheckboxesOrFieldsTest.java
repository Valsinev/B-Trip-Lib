package org.example.utillity;

import org.example.configuration.BusinessTripForm;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BTripGetDaysFromCheckboxesOrFieldsTest {

    BusinessTripForm btripFormMock = mock(BusinessTripForm.class);

    @Test
    void getDaysWithoutNightStay() {

        // Define the behavior of the mock object
        when(btripFormMock.getIsNightStayedInHotel()).thenReturn(false);

        when(btripFormMock.getDay1()).thenReturn(true);
        when(btripFormMock.getDay2()).thenReturn(true);
        when(btripFormMock.getDay4()).thenReturn(true);
        when(btripFormMock.getDay5()).thenReturn(true);
        when(btripFormMock.getDay6()).thenReturn(true);

        // Use the mock object in your test
        List<Integer> result = BTripGetDaysFromCheckboxesOrFields.getDays(btripFormMock);
        List<Integer> expected = List.of(1,2,4,5,6);

        // Assert the result or perform other test logic
        assertEquals(expected, result);

    }

    @Test
    void getDaysWithNightStay() {

        // Define the behavior of the mock object
        when(btripFormMock.getIsNightStayedInHotel()).thenReturn(true);

        when(btripFormMock.getFromWhichDayField()).thenReturn(1);
        when(btripFormMock.getToWhichDayField()).thenReturn(7);

        // Use the mock object in your test
        List<Integer> result = BTripGetDaysFromCheckboxesOrFields.getDays(btripFormMock);
        List<Integer> expected = List.of(1,2,3,4,5,6,7);

        // Assert the result or perform other test logic
        assertEquals(expected, result);

    }
}