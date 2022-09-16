package classes;

import static org.junit.Assert.*;

import classes.Builders.HouseholdBuilder;
import classes.Builders.ResidentBuilder;
import org.junit.Test;
import java.util.List;

public class HouseholdTest {
    @Test
    public void sortResidents_sortsByLastName_caseInsensitive() {
        Household household = new HouseholdBuilder().build();
        Resident resident1 = new ResidentBuilder().withHousehold(household).withLastName("B").build();
        Resident resident2 = new ResidentBuilder().withHousehold(household).withLastName("a").build();
        Resident resident3 = new ResidentBuilder().withHousehold(household).withLastName("c").build();

        List<Resident> actual = household.getSortedResidents();

        assertEquals(null, actual.get(0), resident2);
        assertEquals(null, actual.get(1), resident1);
        assertEquals(null, actual.get(2), resident3);
    }

    @Test
    public void sortResidents_sortsByFirstNameAfterLastName_caseInsensitive() {
        Household household = new HouseholdBuilder().build();
        Resident resident1 = new ResidentBuilder().withHousehold(household).withLastName("A").build();
        Resident resident2 = new ResidentBuilder().withHousehold(household).withLastName("b").withFirstName("A").build();
        Resident resident3 = new ResidentBuilder().withHousehold(household).withLastName("B").withFirstName("c").build();
        Resident resident4 = new ResidentBuilder().withHousehold(household).withLastName("B").withFirstName("b").build();

        List<Resident> actual = household.getSortedResidents();

        assertEquals(null, actual.get(0), resident1);
        assertEquals(null, actual.get(1), resident2);
        assertEquals(null, actual.get(2), resident4);
        assertEquals(null, actual.get(3), resident3);
    }

    @Test
    public void toString_printsAddressAndNumberOfResidents() {
        Household household = new HouseholdBuilder().withStreetAddress("123 main st").withCity("Townsville").withState("WA").build();
        new ResidentBuilder().withLastName("A").withHousehold(household).build();
        new ResidentBuilder().withLastName("B").withHousehold(household).build();
        new ResidentBuilder().withLastName("C").withHousehold(household).build();
        new ResidentBuilder().withLastName("C").withHousehold(household).build();

        String actual = household.toString();

        String expected = "123 main st, Townsville, WA, 3 resident(s)";
        assertEquals(expected, actual);
    }
}
