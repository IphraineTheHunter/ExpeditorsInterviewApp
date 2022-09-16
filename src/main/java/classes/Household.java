package classes;

import java.text.MessageFormat;
import java.util.*;

public class Household {
    public String streetAddress;
    public String city;
    public String state;
    public Set<Resident> residents;

    public Household(String streetAddress, String city, String state) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.residents = new HashSet<>();
    }

    public void addResident(Resident resident) {
        this.residents.add(resident);
        resident.household = this;
    }

    public List<Resident> getSortedResidents() {
        List<Resident> residents = new ArrayList<>(this.residents);

        residents.sort((resident1, resident2) -> {
            if (!resident1.lastName.toUpperCase().equals(resident2.lastName.toUpperCase())) {
                //last names are not equal, so sort by last name
                return resident1.lastName.toUpperCase().compareTo(resident2.lastName.toUpperCase());
            }

            //last names are equal, so sort by first name
            return resident1.firstName.toUpperCase().compareTo(resident2.firstName.toUpperCase());
        });

        return residents;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}, {1}, {2}, {3} resident(s)",
                streetAddress,
                city,
                state,
                residents.size()
        );
    }
}