import classes.Household;
import services.ConsolePrintService;
import services.HouseholdService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ConsolePrintService printer = new ConsolePrintService();

        HouseholdService householdService = new HouseholdService(printer);

        printer.print("Please enter data, pressing enter between data elements. When done, enter 'END' to receive report");

        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        List<String> inputs = new ArrayList<>();

        while (!input.equals("END")) {
            inputs.add(input.trim());
            input = s.nextLine();
        }

        List<Household> households = householdService.processInputs(inputs);

        householdService.printReport(households);
    }
}
