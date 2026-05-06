import java.time.LocalDate;

public class SprintController {

    public Sprint createSprint(String name, String startDate, String endDate) {
        return new Sprint(name, startDate, endDate);
    }

    public void saveSprint(Sprint sprint) {
        Blackboard.getInstance().addSprint(sprint);
    }

    public void addUserStoryToSprint(Sprint selectedSprint, UserStory selectedStory) {
        selectedSprint.addStory(selectedStory);
        Blackboard.getInstance().fireSprintChanged();

    }
}
