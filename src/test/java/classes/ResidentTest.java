package classes;

import classes.Builders.ResidentBuilder;
import org.junit.Test;
import static org.junit.Assert.*;

public class ResidentTest {
    @Test
    public void toString_residentCanGeneratePrintString() {
        Resident resident = new ResidentBuilder().build();

        String expected = "Frodo Baggins, No. 3 Bagshot Row, The Shire, Middle Earth, 33";
        String actual = resident.toString();

        assertEquals(null, expected, actual);
    }
}
