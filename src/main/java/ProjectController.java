//Brian Wallenrod
public class ProjectController {

    public Project createProject(int id, String title, String summary, String type) {
        return new Project(id, title, summary, type);
    }

    public void saveProject(Project project) {
        Blackboard.getInstance().addProject(project);
    }

}