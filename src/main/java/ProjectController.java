public class ProjectController {

    public Project createProject(int id, String title, String summary, String type) {
        return new Project(id, title, summary, type);
    }



}