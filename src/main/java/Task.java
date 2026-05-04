import java.util.ArrayList;

/**

  * This class defines what a task is
  *
  * @author Tyler Page
  * @version 1.0
  *
**/

public class Task {
    private String name;
    private String desc;
    private String status;
    private String assigned;
    private ArrayList<String> tags;

    public Task(String name, String desc, String assigned, ArrayList<String> tags) {
        this.name = name;
        this.desc = desc;
        this.assigned = assigned;
        this.tags = tags;
        this.status = "NEW";
    }
    
    @Override
    public String toString() {
        return "Task: " + name + "\n" +
            "  Description: " + desc + "\n" +
            "  Status: " + status + "\n" +
            "  Assigned to: " + assigned + "\n" +
            "  Tags: " + String.join(", ", tags) + "\n";
    }

    public String getName() { return name; }
    public String getDesc() { return desc; }
    public String getStatus() { return status; }
    public String getAssigned() { return assigned; }
    public void setName(String name) { this.name = name; }
    public void setDesc(String desc) { this.desc = desc; }
    public void setStatus(String status) { this.status = status; }
    public void setAssigned(String assigned) { this.assigned = assigned; }
}