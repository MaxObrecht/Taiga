import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Taiga");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        mainPanel.add(new ProjectUI(cardLayout, mainPanel), "Project");
        mainPanel.add(new TestUI(cardLayout, mainPanel), "Test");
        // mainPanel.add(new UserStoryUI(cardLayout, mainPanel), "UserStory");
        // mainPanel.add(new TaskUI(cardLayout, mainPanel), "Task");
        // mainPanel.add(new SprintUI(cardLayout, mainPanel), "Sprint");

        frame.add(mainPanel);
        frame.setVisible(true);
        cardLayout.show(mainPanel, "Project");
    }

}