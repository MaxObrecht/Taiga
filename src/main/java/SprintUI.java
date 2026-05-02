import javax.swing.*;
import java.time.LocalDate;
import java.util.Scanner;

public class SprintUI extends JPanel {
    private SprintController controller = new SprintController();
    private JTextField title = new JTextField(50);
    private JTextField startDate = new JTextField(10);
    private JTextField endDate = new JTextField(10);

    //make sprint panels methods
    // method for setting visibility of panels

    public void createPanels(){

    }

    public void setVisibility(){

    }

    public void run() {



        //Scanner scanner2 = new Scanner(System.in);
        //SprintController controller = new SprintController();

//        System.out.print("Enter sprint title: ");
//        String name = scanner.nextLine();

//        String[] startDateInput;
//        while (true) {
//            System.out.print("Enter start date: (MM/dd/yyyy)");
//            startDateInput = scanner2.nextLine().split("/");
//            if (startDateInput.length == 3)
//                break;
//            System.out.println("Invalid date input.");
//        }
//        LocalDate startDate = LocalDate.of(Integer.parseInt(startDateInput[2]), Integer.parseInt(startDateInput[1]), Integer.parseInt(startDateInput[0]));
//
//        String[]  endDateInput;
//        while(true) {
//            System.out.print("Enter end date: (MM/dd/yyyy)");
//            endDateInput = scanner2.nextLine().split("/");
//            if (endDateInput.length == 3)
//                break;
//            System.out.println("Invalid date input.");
//        }
//        LocalDate endDate = LocalDate.of(Integer.parseInt(endDateInput[2]), Integer.parseInt(endDateInput[1]), Integer.parseInt(endDateInput[0]));

        //Sprint sprint = controller.createSprint(name, startDate, endDate);
        //controller.saveSprint(sprint);

        //System.out.println("Sprint created successfully!");
    }
}
