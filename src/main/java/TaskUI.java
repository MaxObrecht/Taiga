import java.util.ArrayList;
import java.util.Scanner;

public class TaskUI {
    private static TaskStorage taskStorage = new TaskStorage();

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Task Manager ===");
            System.out.println("1. Create Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createTask(scanner);
                    break;
                case "2":
                    viewTasks();
                    break;
                case "3":
                    // Exit back to StoryUI here
                    System.out.println("Exiting Tasks.");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void createTask(Scanner scanner) {
        System.out.print("Enter task name (story task): ");
        String name = scanner.nextLine();

        System.out.print("Enter description: ");
        String desc = scanner.nextLine();

        System.out.print("Enter assigned person: ");
        String assigned = scanner.nextLine();

        System.out.print("Enter tags (comma separated): ");
        String tagInput = scanner.nextLine();

        ArrayList<String> tags = new ArrayList<>();
        if (!tagInput.trim().isEmpty()) {
            String[] splitTags = tagInput.split(",");
            for (String tag : splitTags) {
                tags.add(tag.trim());
            }
        }

        Task task = new Task(name, desc, assigned, tags);
        taskStorage.addTask(task);

        System.out.println("Task added to story: " + task.getName());
    }

    private static void viewTasks() {
        System.out.println("\nAll Tasks:");
        System.out.println(taskStorage.getTasks());
    }
}