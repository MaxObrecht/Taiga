import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProjectController {

    public Project createProject(int id, String title, String summary, String type) {
        return new Project(id, title, summary, type);
    }

    public void saveProject(Project project) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Project> projects = loadProjects();
        projects.add(project);
        try (FileWriter writer = new FileWriter("projects.json")) {
            gson.toJson(projects, writer);
        } catch (IOException e) {
            System.out.println("Error saving project: " + e.getMessage());
        }
    }

    public List<Project> loadProjects() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("projects.json")) {
            Type listType = new TypeToken<List<Project>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

}