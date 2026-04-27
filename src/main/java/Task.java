import java.util.ArrayList;

public class Task {

    private String name;
    private String desc;
    private String status;
    private String assigned;
    // private Story parentStory;
    private ArrayList<String> tags;

    public Task(String name, String desc, String assigned, ArrayList<String> tags) {
        this.name = name;
        this.desc = desc;
        this.assigned = assigned;
        this.tags = tags;
        this.status = "NEW";
    }

    public String getName() { return name; }
    public String getDesc() { return desc; }
    public String getStatus() { return status; }
    public String getAssigned() { return assigned; }
    public ArrayList<String> getTags() { return tags; }

    public void setStatus(String status) {
        this.status = status;
    }
}