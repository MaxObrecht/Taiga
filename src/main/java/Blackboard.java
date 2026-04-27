import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Blackboard {

    private static Blackboard instance;
    private List<Project> projects;
    private List<UserStory> userstorys;

    private Blackboard() {
        projects = loadProjectsFromFile();
    }

    public static Blackboard getInstance() {
        if (instance == null) {
            instance = new Blackboard();
        }
        return instance;
    }

    public void addProject(Project project) {
        projects.add(project);
        saveProjectsToFile();
    }

    private List<Project> loadProjectsFromFile() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("projects.json")) {
            Type listType = new TypeToken<List<Project>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void saveProjectsToFile() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("projects.json")) {
            gson.toJson(projects, writer);
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void addUserStory(UserStory userstory){
        userstorys.add(userstory);
    }
}