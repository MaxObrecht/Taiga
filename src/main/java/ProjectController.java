import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;

public class ProjectController {

    public Project createProject(int id, String title, String summary, String type) {
        return new Project(id, title, summary, type);
    }

    public void saveProject(Project project) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("projects.json", true)) {
            gson.toJson(project, writer);
        } catch (IOException e) {
            System.out.println("Error saving project: " + e.getMessage());
        }
    }

}