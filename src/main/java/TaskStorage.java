import java.util.ArrayList;

/**

  * This class serves as the storage space for created tasks
  *
  * @author Tyler Page
  * @version 1.0
  *
**/

public class TaskStorage {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}