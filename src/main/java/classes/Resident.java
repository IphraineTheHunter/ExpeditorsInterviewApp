package classes;

import java.text.MessageFormat;

public class Resident {
    public static Integer LEGAL_ADULT_AGE = 18;

    public String firstName;
    public String lastName;
    public Integer age;
    public Household household;

    public Resident(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0} {1}, {2}, {3}, {4}, {5}",
                firstName,
                lastName,
                household.streetAddress,
                household.city,
                household.state,
                age
        );
    }

    @Override
    public boolean equals(Object object) {
        Resident otherResident;
        if (object instanceof Resident) {
            otherResident = (Resident) object;
        }  else {
            return false;
        }

        return this.firstName.equals(otherResident.firstName) &&
                this.lastName.equals(otherResident.lastName) &&
                this.age.equals(otherResident.age);
    }

    @Override
    public int hashCode() {
        return this.firstName.hashCode() * this.lastName.hashCode() * this.age.hashCode();
    }
}
