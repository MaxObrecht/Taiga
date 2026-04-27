import java.util.ArrayList;
import java.util.Scanner;

public class TaskUI {

    private static TaskStorage taskStorage = new TaskStorage();
    private static ArrayList<String> currentTags = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Task Manager ===");
            System.out.println("1. Add Tag");
            System.out.println("2. Create Task");
            System.out.println("3. View Tasks");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addTag(scanner);
                    break;
                case "2":
                    createTask(scanner);
                    break;
                case "3":
                    viewTasks();
                    break;
                case "4":
                    System.out.println("Goodbye 👋");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void addTag(Scanner scanner) {
        System.out.print("Enter tag: ");
        String tag = scanner.nextLine();

        if (!tag.isEmpty()) {
            currentTags.add(tag);
            System.out.println("Current Tags: " + currentTags);
        } else {
            System.out.println("Tag cannot be empty.");
        }
    }

    private static void createTask(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter description: ");
        String desc = scanner.nextLine();

        System.out.print("Enter assigned person: ");
        String assigned = scanner.nextLine();

        Task task = new Task(name, desc, assigned, new ArrayList<>(currentTags));
        taskStorage.addTask(task);

        System.out.println("Created: " + task.getName());

        // Reset tags after creating task
        currentTags.clear();
    }

    private static void viewTasks() {
        System.out.println("\nAll Tasks:");
        System.out.println(taskStorage.getTasks());
    }
}