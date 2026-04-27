import java.util.Scanner;

public class UserStoryUI {

    public void run(){
        Scanner scanner = new Scanner(System.in);
        UserStoryController controller = new UserStoryController();
        System.out.println("Type '+' to add a user story, or 'exit' to quit.");
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equals("+")) {
                System.out.println("\n[+ User Story Clicked]");

                System.out.print("Enter title: ");
                String title = scanner.nextLine();

                System.out.print("Enter description: ");
                String description = scanner.nextLine();
                int estimation;
                while(true) {
                    try {
                        System.out.print("Enter estimation: ");
                        String estimate = scanner.nextLine();
                        estimation = Integer.parseInt(estimate);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Estimation Input: Please enter an integer");
                    }
                }
                System.out.println("\nUser Story Created:");
                System.out.println("Title: " + title);
                System.out.println("Description: " + description);
                System.out.println("Estimation " + estimation);
                UserStory userstory = controller.createUserStory(title, description, estimation);
            } else if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye.");
                break;
            } else {
                System.out.println("Unknown command.");
            }
        }
    }
}

