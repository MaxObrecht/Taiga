public class UserStoryController {
    public UserStory createUserStory(String title, String description, int estimation){
        return new UserStory(title, description, estimation);
    }
    public void saveUserStory(UserStory userstory){
        Blackboard.getInstance().addUserStory(userstory);
    }
}
