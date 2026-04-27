import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Sprint {
    //name, start date, end date, story-list, total points, completed points, open tasks, closed tasks
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Story> storyList;
    private int totalPoints;
    private int completedPoints;
    private int openTasks;
    private int closedTasks;

    public Sprint(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.storyList = new ArrayList<>();
        this.totalPoints = 0;
        this.completedPoints = 0;
        this.openTasks = 0;
        this.closedTasks = 0;
    }

    public List<Story> getStoryList() {
        return storyList;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public int getCompletedPoints() {
        return completedPoints;
    }

    public int getOpenTasks() {
        return openTasks;
    }

    public int getClosedTasks() {
        return closedTasks;
    }

    public void addStory(Story story) {
        storyList.add(story);
    }
}
