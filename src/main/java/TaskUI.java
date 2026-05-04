import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class TaskUI extends JPanel {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private TaskStorage taskStorage = new TaskStorage();

    // Fields for text information
    private JTextField nameField = new JTextField(20);
    private JTextField descField = new JTextField(20);
    private JTextField assignedField = new JTextField(20);
    private JTextField tagField = new JTextField(20);

    private JLabel tagListLabel = new JLabel();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> taskList = new JList<>(listModel);

    private ArrayList<String> currentTags = new ArrayList<>();

    public TaskUI(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(new BorderLayout(10, 10));

        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Tasks"));

        loadTaskList();

        JButton editBtn = new JButton("Edit Task");
        editBtn.addActionListener(e -> editSelectedTask());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(scrollPane, BorderLayout.CENTER);
        topPanel.add(editBtn, BorderLayout.SOUTH);

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createTitledBorder("Create Task"));

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

        JButton addTagBtn = new JButton("Add Tag");
        JButton createBtn = new JButton("Create Task");

        addTagBtn.addActionListener(e -> addTagNanny());
        createBtn.addActionListener(e -> createTaskNanny());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addTagBtn);
        buttonPanel.add(createBtn);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(formPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void loadTaskList() {
        listModel.clear();
        for (Task t : taskStorage.getTasks()) {
            listModel.addElement(t.getName());
        }
    }

    private void createTaskNanny() {
        String name = nameField.getText().trim();
        String desc = descField.getText().trim();
        String assigned = assignedField.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name can't be blank");
            return;
        }

        Task task = new Task(name, desc, assigned, new ArrayList<>(currentTags));
        taskStorage.addTask(task);

        // Reset
        currentTags.clear();
        tagListLabel.setText("");
        nameField.setText("");
        descField.setText("");
        assignedField.setText("");

        loadTaskList();
    }

    private void addTagNanny() {
        String tag = tagField.getText().trim();
        if (!tag.isEmpty()) {
            currentTags.add(tag);
            tagListLabel.setText(String.join(", ", currentTags));
            tagField.setText("");
        }
    }

    private void editSelectedTask() {
        String selected = taskList.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Select a task first.");
            return;
        }

        JOptionPane.showMessageDialog(this, "TODO");
        // cardLayout.show(mainPanel, "TaskEdit");
    }
}

