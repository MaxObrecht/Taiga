import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
//Charles Gallo
public class UserStoryUI extends JPanel implements PropertyChangeListener{
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JTextField estimationField;
    private JTextArea outputArea;

    private UserStoryController controller = new UserStoryController();

    public UserStoryUI() {
        Blackboard.getInstance().addObserver(this);

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel header = new JLabel("Create User Story");
        header.setFont(new Font("Arial", Font.BOLD, 22));
        add(header, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(0, 1, 5, 5));

        titleField = new JTextField();
        descriptionArea = new JTextArea(4, 20);
        estimationField = new JTextField();

        formPanel.add(new JLabel("Title:"));
        formPanel.add(titleField);

        formPanel.add(new JLabel("Description:"));
        formPanel.add(new JScrollPane(descriptionArea));

        formPanel.add(new JLabel("Estimation:"));
        formPanel.add(estimationField);

        JButton createButton = new JButton("+ Add User Story");
        createButton.addActionListener(e -> createUserStory());

        JButton backButton = new JButton("Back to Project");
        backButton.addActionListener(e -> ViewsManager.getInstance().showPanel("Project"));

        formPanel.add(createButton);
        formPanel.add(backButton);

        add(formPanel, BorderLayout.CENTER);

        outputArea = new JTextArea(8, 30);
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);
    }

    private void createUserStory() {
        String title = titleField.getText().trim();
        String description = descriptionArea.getText().trim();
        String estimateText = estimationField.getText().trim();

        if (title.isEmpty() || estimateText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        int estimation;

        try {
            estimation = Integer.parseInt(estimateText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Estimation must be an integer.");
            return;
        }

        controller.createUserStory(title, description, estimation);
    }
    private void loadUserStories() {
        outputArea.setText("");
        List<UserStory> stories = Blackboard.getInstance().getUserStorys();
        for (UserStory story : stories) {
            outputArea.append("User Story:\n");
            outputArea.append("Title: " + story.getTitle() + "\n");
            outputArea.append("Description: " + story.getDescription() + "\n");
            outputArea.append("Estimation: " + story.getEstimation() + "\n");
            outputArea.append("-------------------------\n");
        }
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("userstorys")) {
            loadUserStories();
        }
    }
}