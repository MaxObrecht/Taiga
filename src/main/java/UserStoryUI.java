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
    //private JTextArea outputArea;

    private JList<UserStory> userStoryList;
    private DefaultListModel<UserStory> userStoryListModel;
    private JComboBox<Sprint> sprintComboBox;

    private UserStoryController controller = new UserStoryController();
    private SprintController sprintcontroller = new SprintController();

    public UserStoryUI() {
        Blackboard.getInstance().addObserver(this);

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel header = new JLabel("User Stories");
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
        formPanel.add(createButton);

        formPanel.add(new JSeparator());

        sprintComboBox = new JComboBox<>();

        formPanel.add(new JLabel("Select Sprint:"));
        formPanel.add(sprintComboBox);

        JButton moveToSprintButton = new JButton("Move Selected Story to Sprint");
        moveToSprintButton.addActionListener(e -> moveStoryToSprint());
        formPanel.add(moveToSprintButton);

        JButton backButton = new JButton("Back to Project");
        backButton.addActionListener(e -> ViewsManager.getInstance().showPanel("Project"));
        formPanel.add(backButton);

        add(formPanel, BorderLayout.CENTER);

        userStoryListModel = new DefaultListModel<>();
        userStoryList = new JList<>(userStoryListModel);
        userStoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(userStoryList), BorderLayout.SOUTH);

        loadUserStories();
        JButton createSprintButton = new JButton("+ Create Sprint");
        createSprintButton.addActionListener(e -> ViewsManager.getInstance().showPanel("Sprint"));
        formPanel.add(createSprintButton);
        //refreshSprintComboBox();
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
        titleField.setText("");
        descriptionArea.setText("");
        estimationField.setText("");
    }

    private void moveStoryToSprint() {
        UserStory selectedStory = userStoryList.getSelectedValue();
        Sprint selectedSprint = (Sprint) sprintComboBox.getSelectedItem();

        if (selectedStory == null || selectedSprint == null) {
            JOptionPane.showMessageDialog(this, "Please select both a user story and a sprint.");
            return;
        }

        //sprintcontroller.addUserStoryToSprint(selectedSprint, selectedStory);
    }

    private void loadUserStories() {
        userStoryListModel.clear();

        List<UserStory> stories = Blackboard.getInstance().getUserStorys();

        for (UserStory story : stories) {
            userStoryListModel.addElement(story);
        }
    }

//    private void refreshSprintComboBox() {
//        sprintComboBox.removeAllItems();
//
//        List<Sprint> sprints = Blackboard.getInstance().getSprints();
//
//        for (Sprint sprint : sprints) {
//            sprintComboBox.addItem(sprint);
//        }
//    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("userstorys")) {
            loadUserStories();
        }
    }

//    @Override
//    public String toString() {
//        return title;
//    }
}