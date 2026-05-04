public class Main {
    public static void main(String[] args) {
        ViewsManager vm = ViewsManager.getInstance();
        vm.addPanel(new LoginUI(), "Login");
        vm.addPanel(new ProjectUI(), "Project");
        vm.addPanel(new UserStoryUI(), "UserStory");
        //vm.addPanel(new SprintUI(), "Sprint");
        //vm.addPanel(new TaskUI(), "Task");
        vm.addPanel(new TestUI(), "Test");

        vm.start("Login");
    }
}