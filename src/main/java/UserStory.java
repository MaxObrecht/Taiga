import java.util.ArrayList;
import java.util.List;
public class UserStory {
    private String title;
    private String description;
    private int estimation;
    private List<Task> tasks;
    private boolean assignedToSprint;

    public UserStory(String title, String description, int estimation) {
        this.title = title;
        this.description = description;
        this.estimation = estimation;
        this.tasks = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getEstimation() {
        return estimation;
    }

    public List<Task> getTasks(){
        return tasks;
    }

    public boolean isAssignedToSprint(){
        return assignedToSprint;
    }

    public void setAssignedToSprint(boolean assigned){
        this.assignedToSprint = assigned;
    }

    @Override
    public String toString(){
        return getTitle();
    }
}
