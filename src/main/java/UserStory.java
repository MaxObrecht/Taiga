import java.util.Scanner;
public class UserStory {
    private String title;
    private String description;
    private int estimation;

    public UserStory(String title, String description, int estimation) {
        this.title = title;
        this.description = description;
        this.estimation = estimation;
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
}
