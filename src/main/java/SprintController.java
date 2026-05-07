/**

 * This class is doing the following ...
 *
 * Handles the logic for creating and saving sprints, as well as adding stories to sprints.
 * When stories are added to sprints an update is fired to refresh the sprintboard UI
 *
 * @author Max Obrecht
 * @version 1.0
 *
 */
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
