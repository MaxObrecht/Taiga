import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class ProjectUI extends JFrame {
    private ProjectController projectController = new ProjectController();

    private JTextField titleField = new JTextField(20);
    private JTextField summaryField = new JTextField(20);
    private JComboBox<String> typeDropdown = new JComboBox<>(new String[]{"Scrum", "Kanban"});
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> projectList = new JList<>(listModel);

    public void run() {
        setTitle("Project Manager");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // --- Top: Scrollable project list ---
        projectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(projectList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Select a Project"));
        scrollPane.setPreferredSize(new Dimension(600, 200));
        loadProjectList();

        JButton selectBtn = new JButton("Open Project");
        selectBtn.addActionListener(e -> openSelectedProject());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(scrollPane, BorderLayout.CENTER);
        topPanel.add(selectBtn, BorderLayout.SOUTH);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        // --- Bottom: Create new project form ---
        JPanel formPanel = new JPanel(new java.awt.GridLayout(0, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createTitledBorder("Create New Project"));

        formPanel.add(new JLabel("Title"));
        formPanel.add(titleField);

        formPanel.add(new JLabel("Summary"));
        formPanel.add(summaryField);

        formPanel.add(new JLabel("Type"));
        formPanel.add(typeDropdown);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        JButton createBtn = new JButton("Create Project");
        buttonPanel.add(createBtn);
        createBtn.addActionListener(e -> createProjectNanny());

        bottomPanel.add(formPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        add(topPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadProjectList() {
        listModel.clear();
        List<Project> projects = Blackboard.getInstance().getProjects();
        for (Project p : projects) {
            listModel.addElement(p.getTitle());
        }
    }

    private void openSelectedProject() {
        String selected = projectList.getSelectedValue();
        if (selected == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a project first.");
            return;
        }
        // TODO: switch to UserStoryUI, passing the selected project
        System.out.println("Opening project: " + selected);
    }

    private void createProjectNanny() {
        String title = titleField.getText().trim();
        String summary = summaryField.getText().trim();
        String type = (String) typeDropdown.getSelectedItem();

        if (title.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Title cannot be empty.");
            return;
        }

        Project project = projectController.createProject(1, title, summary, type);
        projectController.saveProject(project);

        System.out.println("Created: " + project.getTitle());

        titleField.setText("");
        summaryField.setText("");
        typeDropdown.setSelectedIndex(0);
        loadProjectList();
    }
}