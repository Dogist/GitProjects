/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.holidayconsultant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author masix
 */
public class HolidayConsultant {

    private List<HolidayDestination> holidayDestinations;

    public HolidayConsultant() {
        holidayDestinations = new ArrayList<>();
    }

    public HolidayConsultant(List<HolidayDestination> holidayDestinations) {
        this.holidayDestinations = holidayDestinations;
    }

    public void addHolidayDestination(HolidayDestination e) {
        holidayDestinations.add(e);
    }

    public void removeHolidayDestination(HolidayDestination o) {
        holidayDestinations.remove(o);
    }

    public List<HolidayDestination> getFittingDestinations(Predicate<HolidayDestination> filter) {
        return this.holidayDestinations.stream().filter(filter).collect(Collectors.toList());
    }

    public List<HolidayDestination> getDestinationsWithComfortAndSportOptions(HotelCategory requiredHotelCategory, String... requiredSportOptions) {
        return this.getFittingDestinations((d) -> {
            return Objects.equals(d.getCategory(), requiredHotelCategory)
                    && (requiredSportOptions != null ? d.getSportOptionList().containsAll(Arrays.asList(requiredSportOptions)) : true);
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    }

}
