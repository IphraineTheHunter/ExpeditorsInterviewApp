package services;

import classes.Household;
import classes.Resident;

import java.util.ArrayList;
import java.util.List;

public class HouseholdService {
    private ConsolePrintService consolePrintService;

    public HouseholdService(ConsolePrintService consolePrintService) {
        this.consolePrintService = consolePrintService;
    }

    public List<Household> processInputs(List<String> inputs) {
        List<Household> households = new ArrayList<>();

        if (inputs.size() % 6 != 0) {
            throw new IllegalArgumentException("");
        }

        for(int row = 0; row<inputs.size()/6; row++) {
            String firstName = inputs.get((row * 6));
            String lastName = inputs.get((row * 6) + 1);
            String streetAddress = inputs.get((row * 6) + 2);
            String city = inputs.get((row * 6) + 3);
            String state = inputs.get((row * 6) + 4);
            Integer age = Integer.decode(inputs.get((row * 6) + 5));

            if (age == null) {
                throw new IllegalArgumentException("Age must be a number");
            }

            Resident resident = new Resident(firstName, lastName, age);

            boolean householdFound = false;
            for (Household household : households) {
                if (
                    sanitizeString(household.streetAddress)
                            .equals(sanitizeString(streetAddress)) &&
                    sanitizeString(household.city)
                            .equals(sanitizeString(city.toUpperCase())) &&
                    sanitizeString(household.state.toUpperCase())
                            .equals(sanitizeString(state.toUpperCase()))
                ) {
                    //household already exist, so add resident to it
                    household.addResident(resident);
                    householdFound = true;
                }
            }

            if (!householdFound) {
                //we didn't find the household, so add it to the set
                Household household = new Household(streetAddress, city, state);
                household.addResident(resident);
                households.add(household);
            }
        }

        return households;
    }

    public void printReport(List<Household> households) {
        for (Household household : households) {
            consolePrintService.print(household.toString());
            for (Resident resident : household.getSortedResidents()) {
                if (resident.age >= Resident.LEGAL_ADULT_AGE) {
                    consolePrintService.print(resident.toString());
                }
            }
        }
    }

    private String sanitizeString(String input) {
        return input.toUpperCase()
                .replace(",","")
                .replace(".","")
                .trim();
    }
}
