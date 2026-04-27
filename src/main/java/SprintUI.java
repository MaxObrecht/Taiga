import java.time.LocalDate;
import java.util.Scanner;

public class SprintUI {
    public void run() {
        Scanner scanner = new Scanner(System.in);
        SprintController controller = new SprintController();

        System.out.print("Enter sprint title: ");
        String name = scanner.nextLine();

        String[] startDateInput;
        while (true) {
            System.out.print("Enter start date: (MM/dd/yyyy)");
            startDateInput = scanner.nextLine().split("/");
            if (startDateInput.length == 3)
                break;
            System.out.println("Invalid date input.");
        }
        LocalDate startDate = LocalDate.of(Integer.parseInt(startDateInput[0]), Integer.parseInt(startDateInput[1]), Integer.parseInt(startDateInput[2]));

        String[]  endDateInput;
        while(true) {
            System.out.print("Enter end date: (MM/dd/yyyy)");
            endDateInput = scanner.nextLine().split("/");
            if (endDateInput.length == 3)
                break;
            System.out.println("Invalid date input.");
        }
        LocalDate endDate = LocalDate.of(Integer.parseInt(endDateInput[0]), Integer.parseInt(endDateInput[1]), Integer.parseInt(endDateInput[2]));

        Sprint sprint = controller.createSprint(name, startDate, endDate);
        //controller.saveSprint(sprint);

        System.out.println("Sprint created successfully!");
        scanner.close();
    }
}
