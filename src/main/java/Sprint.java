/**

 * This class is doing the following ...
 *
 * Constructor for Sprint with setters and getters
 *
 * @author Max Obrecht
 * @version 1.0
 *
 */
import java.util.ArrayList;
import java.util.List;

public class Sprint {
    private String name;
    private String startDate;
    private String endDate;
    private List<UserStory> storyList;
    private int totalPoints;
    private int completedPoints;
    private int openTasks;
    private int closedTasks;

    public Sprint(String name, String startDate, String endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.storyList = new ArrayList<>();
        this.totalPoints = 0;
        this.completedPoints = 0;
        this.openTasks = 0;
        this.closedTasks = 0;
    }

    public List<UserStory> getStoryList() {
        return this.storyList;
    }

    public int getTotalPoints() {
        return this.totalPoints;
    }

    public int getCompletedPoints() {
        return this.completedPoints;
    }

    public int getOpenTasks() {
        return this.openTasks;
    }

    public int getClosedTasks() {
        return this.closedTasks;
    }

    public void addStory(UserStory story) {
        storyList.add(story);
    }

    public String getName() {
        return this.name;
    }
    @Override
    public String toString() {
        return name;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }
}
