import javax.swing.*;
import java.awt.*;

public class UserStoryUI extends JPanel {

    private JTextField titleField;
    private JTextArea descriptionArea;
    private JTextField estimationField;
    private JTextArea outputArea;

    private UserStoryController controller;

    public UserStoryUI(CardLayout cardLayout, JPanel mainPanel) {
        controller = new UserStoryController();

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
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Project"));

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

        if (title.isEmpty() || description.isEmpty() || estimateText.isEmpty()) {
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

        UserStory userStory = controller.createUserStory(title, description, estimation);

        outputArea.append("User Story Created:\n");
        outputArea.append("Title: " + title + "\n");
        outputArea.append("Description: " + description + "\n");
        outputArea.append("Estimation: " + estimation + "\n");
        outputArea.append("-------------------------\n");

        titleField.setText("");
        descriptionArea.setText("");
        estimationField.setText("");
    }
}