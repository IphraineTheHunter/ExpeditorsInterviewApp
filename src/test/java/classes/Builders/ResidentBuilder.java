package classes.Builders;

import classes.Household;
import classes.Resident;

public class ResidentBuilder {
    private Resident resident;

    public ResidentBuilder() {
        resident = new Resident("Frodo", "Baggins", 33);
        Household household = new HouseholdBuilder().build();
        household.addResident(resident);
    }

    public ResidentBuilder withFirstName(String firstName) {
        resident.firstName = firstName;
        return this;
    }

    public ResidentBuilder withLastName(String lastName) {
        resident.lastName = lastName;
        return this;
    }

    public ResidentBuilder withAge(Integer age) {
        resident.age = age;
        return this;
    }

    public ResidentBuilder withHousehold(Household household) {
        household.addResident(resident);
        return this;
    }

    public Resident build() {
        return resident;
    }
}
