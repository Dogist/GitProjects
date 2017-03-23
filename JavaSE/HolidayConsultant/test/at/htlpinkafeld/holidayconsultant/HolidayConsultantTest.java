/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.holidayconsultant;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author masix
 */
public class HolidayConsultantTest {

    private HolidayConsultant consultant;
    private HolidayDestination holidayDestinationMayorca;
    private HolidayDestination holidayDestinationSizilien;
    private HolidayDestination holidayDestinationPrag;

    @Before
    public void setUp() {

        holidayDestinationMayorca = new HolidayDestination("Mayorca", "Greece", HotelCategory.SUPERIOR, Arrays.asList("golf", "tennis", "football"));
        holidayDestinationSizilien = new HolidayDestination("Sizilien", "Italy", HotelCategory.LUXUS, Arrays.asList("swimming", "golf"));
        holidayDestinationPrag = new HolidayDestination("Prag", "Hungary", HotelCategory.LUXUS, Arrays.asList());

        consultant = new HolidayConsultant(
                new LinkedList<>(Arrays.asList(
                        holidayDestinationMayorca,
                        holidayDestinationSizilien,
                        holidayDestinationPrag)));

    }

    /**
     * Test of getFittingDestinations method, of class HolidayConsultant.
     */
    @Test
    public void testGetFittingDestinations() {
        System.out.println("getFittingDestinations");
        Predicate<HolidayDestination> filter = (t) -> {
            return t.getCategory().equals(HotelCategory.SUPERIOR);
        };

        List<HolidayDestination> expResult = Arrays.asList(holidayDestinationMayorca);
        List<HolidayDestination> result = consultant.getFittingDestinations(filter);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDestinationsWithComfortAndSportOptions method, of class
     * HolidayConsultant.
     */
    @Test
    public void testGetDestinationsWithComfortAndSportOptions() {
        System.out.println("getDestinationsWithComfortAndSportOptions");

        List<HolidayDestination> expResult = Arrays.asList(
                holidayDestinationSizilien,
                holidayDestinationPrag);
        List<HolidayDestination> result = consultant.getDestinationsWithComfortAndSportOptions(HotelCategory.LUXUS);

        assertEquals(expResult, result);

        expResult = Arrays.asList(
                new HolidayDestination("Sizilien", "Italy", HotelCategory.LUXUS, Arrays.asList("swimming", "golf")));
        result = consultant.getDestinationsWithComfortAndSportOptions(HotelCategory.LUXUS, new String[]{"swimming"});

        assertEquals(expResult, result);

    }

}
