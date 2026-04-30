import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TaskUI extends JFrame {
    private TaskStorage taskStorage = new TaskStorage();

    // Fields for text information
    private JTextField nameField = new JTextField(20);
    private JTextField descField = new JTextField(20);
    private JTextField assignedField = new JTextField(20);
    private JTextField tagField = new JTextField(20);
    private JLabel tagListLabel = new JLabel();
    private ArrayList<String> currentTags = new ArrayList<>();

    // Run this when clicking create task button for a story
    public void run() {
        setTitle("Task Manager");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new java.awt.BorderLayout());

        JPanel formPanel = new JPanel(new java.awt.GridLayout(0, 2, 8, 8));
        JPanel buttonPanel = new JPanel();

        formPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 5));

        formPanel.add(new JLabel("Name"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Description"));
        formPanel.add(descField);

        formPanel.add(new JLabel("Assigned"));
        formPanel.add(assignedField);

        formPanel.add(new JLabel("Add Tag"));
        formPanel.add(tagField);

        formPanel.add(new JLabel("Current Tags"));
        formPanel.add(tagListLabel);

        JButton createBtn = new JButton("Create Task");
        JButton addTagBtn = new JButton("Add Tag");
        // For testing task adding functionality 
        JButton retrieveTask = new JButton("Retrieve Task");
        buttonPanel.add(createBtn);
        buttonPanel.add(addTagBtn);
        buttonPanel.add(retrieveTask);

        createBtn.addActionListener(e -> createTaskNanny());
        addTagBtn.addActionListener(e -> addTagNanny(tagField.getText()));
        retrieveTask.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (Task task : taskStorage.getTasks()) {
                sb.append(task.toString()).append("\n");
            }

            javax.swing.JOptionPane.showMessageDialog(this, sb.toString());
        });

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void createTaskNanny() {
        String name = nameField.getText();
        String desc = descField.getText();
        String assigned = assignedField.getText();

        Task task = new Task(name, desc, assigned, new ArrayList<>(currentTags));
        taskStorage.addTask(task);
        System.out.println("Created: " + task.getName());

        // Reset everything after creating task
        currentTags.clear();
        tagListLabel.setText("");
        nameField.setText("");
        descField.setText("");
        assignedField.setText("");
    }

    public void addTagNanny(String tag) {
        if (!tag.isEmpty()) {
            currentTags.add(tag);
            tagListLabel.setText(String.join(", ", currentTags));
            tagField.setText(""); // clear input
        }
    }
}

