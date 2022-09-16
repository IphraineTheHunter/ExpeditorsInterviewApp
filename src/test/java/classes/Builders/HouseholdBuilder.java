package classes.Builders;

import classes.Household;

public class HouseholdBuilder {
    private Household household;

    public HouseholdBuilder() {
        household = new Household("No. 3 Bagshot Row", "The Shire", "Middle Earth");
    }

    public HouseholdBuilder withStreetAddress(String streetAddress) {
        household.streetAddress = streetAddress;
        return this;
    }

    public HouseholdBuilder withCity(String city) {
        household.city = city;
        return this;
    }

    public HouseholdBuilder withState(String state) {
        household.state = state;
        return this;
    }

    public Household build() {
        return household;
    }
}
