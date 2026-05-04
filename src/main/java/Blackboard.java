import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Blackboard {

    private static Blackboard instance;
    private List<Project> projects;
    private List<Sprint> sprints;
    private List<UserStory> userstorys;
    private List<Task> tasks;

    private Blackboard() {
        projects = loadProjectsFromFile();
        sprints = getSprints();
        //userstorys = getUserStorys();
        userstorys = new ArrayList<>();
        tasks = getTasks();
    }

    public static Blackboard getInstance() {
        if (instance == null) {
            instance = new Blackboard();
        }
        return instance;
    }

    public void addProject(Project project) {
        projects.add(project);
        support.firePropertyChange("projects", null, projects);
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

    public List<Project> getProjects() {
        return projects;
    }

    public void addSprint(Sprint sprint) {
        sprints.add(sprint);
    }

    public List<Sprint> getSprints() { return sprints; }

    public void addUserStory(UserStory userstory){
        userstorys.add(userstory);
        support.firePropertyChange("userstorys", null, userstorys);
    }
    public List<UserStory> getUserStorys() {
        return userstorys;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
    public List<Task> getTasks() {
        return tasks;
    }

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void addObserver(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removeObserver(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

}