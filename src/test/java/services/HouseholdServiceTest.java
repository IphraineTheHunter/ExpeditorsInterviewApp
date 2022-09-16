package services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;

import classes.Household;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class HouseholdServiceTest {
    @Test
    public void processInputs_givenTestCase() {
        ConsolePrintService consolePrintService = Mockito.mock(ConsolePrintService.class);
        HouseholdService householdService = new HouseholdService(consolePrintService);

        List<Household> actual = householdService.processInputs(Arrays.asList("Dave", "Smith", "123 main st.", "seattle", "wa", "43",
                "Alice", "Smith", "123 Main St.", "Seattle", "WA", "45",
                "Bob", "Williams", "234 2nd Ave.", "Tacoma", "WA", "26",
                "Carol", "Johnson", "234 2nd Ave", "Seattle", "WA", "67",
                "Eve", "Smith", "234 2nd Ave.", "Tacoma", "WA", "25",
                "Frank", "Jones", "234 2nd Ave.", "Tacoma", "FL", "23",
                "George", "Brown", "345 3rd Blvd., Apt. 200", "Seattle", "WA", "18",
                "Helen", "Brown", "345 3rd Blvd. Apt. 200", "Seattle", "WA", "18",
                "Ian", "Smith", "123 main st ", "Seattle", "Wa", "18",
                "Jane", "Smith", "123 Main St.", "Seattle", "WA", "13"));

        assertEquals(null, 5, actual.size());
    }

    @Test
    public void processInputs_wrongNumberOfStringsThrowsException() {
        ConsolePrintService consolePrintService = Mockito.mock(ConsolePrintService.class);
        HouseholdService householdService = new HouseholdService(consolePrintService);

        try {
            householdService.processInputs(Arrays.asList(
                    "Dave","Smith","123 main st.","seattle","wa"
            ));
            fail();
        } catch (IllegalArgumentException e) {
            //do nothing as we were expecting the exception
        }
    }

    @Captor
    ArgumentCaptor<String> printCaptor;

    @Test
    public void printReport_givenTestCase() {
        ConsolePrintService consolePrintService = Mockito.mock(ConsolePrintService.class);
        HouseholdService householdService = new HouseholdService(consolePrintService);

        List<Household> households = householdService.processInputs(Arrays.asList(
                "Dave","Smith","123 main st.","seattle","wa","43",
                "Alice","Smith","123 Main St.","Seattle","WA","45",
                "Bob","Williams","234 2nd Ave.","Tacoma","WA","26",
                "Carol","Johnson","234 2nd Ave","Seattle","WA","67",
                "Eve","Smith","234 2nd Ave.","Tacoma","WA","25",
                "Frank","Jones","234 2nd Ave.","Tacoma","FL","23",
                "George","Brown","345 3rd Blvd., Apt. 200","Seattle","WA","18",
                "Helen","Brown","345 3rd Blvd. Apt. 200","Seattle","WA","18",
                "Ian","Smith","123 main st ","Seattle","Wa","18",
                "Jane","Smith","123 Main St.","Seattle","WA","13"
        ));


        householdService.printReport(households);

        Mockito.verify(consolePrintService, times(14)).print(printCaptor.capture());
        List<String> printedLines = printCaptor.getAllValues();

        assertEquals("123 main st., seattle, wa, 4 resident(s)", printedLines.get(0));
        assertEquals("Alice Smith, 123 main st., seattle, wa, 45", printedLines.get(1));
        assertEquals("Dave Smith, 123 main st., seattle, wa, 43", printedLines.get(2));
        assertEquals("Ian Smith, 123 main st., seattle, wa, 18", printedLines.get(3));
        assertEquals("234 2nd Ave., Tacoma, WA, 2 resident(s)", printedLines.get(4));
        assertEquals("Eve Smith, 234 2nd Ave., Tacoma, WA, 25", printedLines.get(5));
        assertEquals("Bob Williams, 234 2nd Ave., Tacoma, WA, 26", printedLines.get(6));
        assertEquals("234 2nd Ave, Seattle, WA, 1 resident(s)", printedLines.get(7));
        assertEquals("Carol Johnson, 234 2nd Ave, Seattle, WA, 67", printedLines.get(8));
        assertEquals("234 2nd Ave., Tacoma, FL, 1 resident(s)", printedLines.get(9));
        assertEquals("Frank Jones, 234 2nd Ave., Tacoma, FL, 23", printedLines.get(10));
        assertEquals("345 3rd Blvd., Apt. 200, Seattle, WA, 2 resident(s)", printedLines.get(11));
        assertEquals("George Brown, 345 3rd Blvd., Apt. 200, Seattle, WA, 18", printedLines.get(12));
        assertEquals("Helen Brown, 345 3rd Blvd., Apt. 200, Seattle, WA, 18", printedLines.get(13));
    }
}
