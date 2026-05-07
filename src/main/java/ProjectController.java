/**
 * Handles the business logic for creating and saving projects.
 * Acts as the intermediary between ProjectUI and the Blackboard.
 *
 * @author Brian Wallenrod
 * @version 2.0
 */
public class ProjectController {

    public Project createProject(int id, String title, String summary, String type) {
        return new Project(id, title, summary, type);
    }

    public void saveProject(Project project) {
        Blackboard.getInstance().addProject(project);
    }

}