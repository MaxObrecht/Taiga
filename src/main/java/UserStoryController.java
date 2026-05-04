public class UserStoryController {
    public UserStory createUserStory(String title, String description, int estimation){
        UserStory userstory = new UserStory(title, description, estimation);
        Blackboard.getInstance().addUserStory(userstory);
        return userstory;
    }
    public void saveUserStory(UserStory userstory){
        Blackboard.getInstance().addUserStory(userstory);
    }
}
