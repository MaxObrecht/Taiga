import java.util.Scanner;

public class ProjectUI {

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ProjectController controller = new ProjectController();

        System.out.print("Enter project title: ");
        String title = scanner.nextLine();

        System.out.print("Enter project summary: ");
        String summary = scanner.nextLine();

        String type;
        while (true) {
            System.out.print("Enter project type (Scrum/Kanban): ");
            type = scanner.nextLine();
            if (type.equalsIgnoreCase("Scrum") || type.equalsIgnoreCase("Kanban")) {
                break;
            }
            System.out.println("Invalid type. Please enter Scrum or Kanban.");
        }

        Project project = controller.createProject(1, title, summary, type);
        controller.saveProject(project);

        System.out.println("Project created successfully!");
    }

}