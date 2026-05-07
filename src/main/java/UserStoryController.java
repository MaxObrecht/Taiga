/**This class is the controller for the user stories
 * and is responsible for sending userstories to the blackboard
 *
 * @author Charles Gallo
 */
public class UserStoryController {
    public UserStory createUserStory(String title, String description, int estimation){
        return new UserStory(title, description, estimation);
    }
    public void saveUserStory(UserStory userstory){
        Blackboard.getInstance().addUserStory(userstory);
    }
}
