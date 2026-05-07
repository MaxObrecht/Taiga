/**

 * This class is doing the following ...
 *
 * Handles the observer logic SprintBoardUI. The SpintBoard holds stories, tasks, and information about
 * the progress of the sprint. Refreshes when stories and tasks are added. Initiates fires to the task UI.
 *
 * @author Max Obrecht
 * @version 1.0
 *
 */
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SprintBoardUI extends JPanel implements PropertyChangeListener {

    private Sprint sprint;
    private UserStory storyWaitingForTask;

    private JLabel sprintNameLabel = new JLabel();
    private JLabel sprintDatesLabel = new JLabel();
    private JLabel totalStoriesLabel = new JLabel();
    private JLabel totalTasksLabel = new JLabel();
    private JLabel closedTasksLabel = new JLabel();
    private JLabel progressLabel = new JLabel();

    private JPanel rowsPanel;

    public SprintBoardUI(Sprint sprint) {
        this.sprint = sprint;

        Blackboard.getInstance().addObserver(this);

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        add(buildTopPanel(), BorderLayout.NORTH);
        add(buildBoardPanel(), BorderLayout.CENTER);

        refreshBoard();
    }

    private JPanel buildTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));

        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        sprintNameLabel.setFont(new Font("Arial", Font.BOLD, 24));

        titlePanel.add(sprintNameLabel);
        titlePanel.add(sprintDatesLabel);

        JPanel statsPanel = new JPanel(new GridLayout(1, 4, 8, 8));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Sprint Progress"));

        statsPanel.add(totalStoriesLabel);
        statsPanel.add(totalTasksLabel);
        statsPanel.add(closedTasksLabel);
        statsPanel.add(progressLabel);

        JButton backButton = new JButton("Back to User Stories");
        backButton.addActionListener(e -> ViewsManager.getInstance().showPanel("UserStory"));

        topPanel.add(titlePanel, BorderLayout.NORTH);
        topPanel.add(statsPanel, BorderLayout.CENTER);
        topPanel.add(backButton, BorderLayout.EAST);

        return topPanel;
    }

    private JPanel buildBoardPanel() {
        JPanel boardPanel = new JPanel(new BorderLayout(5, 5));

        JPanel headerRow = new JPanel(new GridLayout(1, 5, 5, 5));
        headerRow.add(makeHeaderLabel("USER STORY"));
        headerRow.add(makeHeaderLabel("NEW"));
        headerRow.add(makeHeaderLabel("IN PROGRESS"));
        headerRow.add(makeHeaderLabel("READY FOR TEST"));
        headerRow.add(makeHeaderLabel("CLOSED"));

        rowsPanel = new JPanel();
        rowsPanel.setLayout(new BoxLayout(rowsPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(rowsPanel);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Sprint Board"));

        boardPanel.add(headerRow, BorderLayout.NORTH);
        boardPanel.add(scrollPane, BorderLayout.CENTER);

        return boardPanel;
    }

    private JLabel makeHeaderLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        return label;
    }

    private void refreshBoard() {
        refreshSummary();

        rowsPanel.removeAll();

        for (UserStory story : sprint.getStoryList()) {
            rowsPanel.add(buildStoryRow(story));
        }

        rowsPanel.revalidate();
        rowsPanel.repaint();
    }

    private void refreshSummary() {
        int totalStories = sprint.getStoryList().size();
        int totalTasks = 0;
        int closedTasks = 0;

        for (UserStory story : sprint.getStoryList()) {
            for (Task task : story.getTasks()) {
                totalTasks++;

                if (task.getStatus().equals("CLOSED")) {
                    closedTasks++;
                }
            }
        }

        int progress = totalTasks == 0 ? 0 : (closedTasks * 100 / totalTasks);

        sprintNameLabel.setText("Sprint: " + sprint.getName());
        sprintDatesLabel.setText("Dates: " + sprint.getStartDate() + " to " + sprint.getEndDate());

        totalStoriesLabel.setText("Stories: " + totalStories);
        totalTasksLabel.setText("Tasks: " + totalTasks);
        closedTasksLabel.setText("Closed: " + closedTasks);
        progressLabel.setText("Progress: " + progress + "%");
    }

    private JPanel buildStoryRow(UserStory story) {
        JPanel row = new JPanel(new GridLayout(1, 5, 5, 5));
        row.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        row.add(buildStoryCell(story));
        row.add(buildTaskColumn(story, "NEW"));
        row.add(buildTaskColumn(story, "IN_PROGRESS"));
        row.add(buildTaskColumn(story, "READY_FOR_TEST"));
        row.add(buildTaskColumn(story, "CLOSED"));

        return row;

    }

    private JPanel buildStoryCell(UserStory story) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JTextArea storyInfo = new JTextArea(
                story.getTitle() + "\n\n" +
                        story.getDescription() + "\n\n" +
                        "Estimate: " + story.getEstimation()
        );

        storyInfo.setEditable(false);
        storyInfo.setLineWrap(true);
        storyInfo.setWrapStyleWord(true);

        JButton addTaskButton = new JButton("+ Task");
        addTaskButton.addActionListener(e -> openTaskUIForStory(story));

        panel.add(storyInfo, BorderLayout.CENTER);
        panel.add(addTaskButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel buildTaskColumn(UserStory story, String status) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        for (Task task : story.getTasks()) {
            if (task.getStatus().equals(status)) {
                panel.add(buildTaskCard(task));
            }
        }

        return panel;
    }

    private JPanel buildTaskCard(Task task) {
        JPanel card = new JPanel(new BorderLayout(5, 5));
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JTextArea taskInfo = new JTextArea(
                task.getName() + "\n" +
                        task.getDesc() + "\n" +
                        "Assigned: " + task.getAssigned()
        );

        taskInfo.setEditable(false);
        taskInfo.setLineWrap(true);
        taskInfo.setWrapStyleWord(true);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton leftButton = new JButton("<");
        leftButton.addActionListener(e -> moveTaskLeft(task));

        JButton rightButton = new JButton(">");
        rightButton.addActionListener(e -> moveTaskRight(task));

        buttonPanel.add(leftButton);
        buttonPanel.add(rightButton);

        card.add(taskInfo, BorderLayout.CENTER);
        card.add(buttonPanel, BorderLayout.SOUTH);

        return card;
    }

    private void openTaskUIForStory(UserStory story) {
        storyWaitingForTask = story;
        ViewsManager.getInstance().showPanel("Task");
    }

    private void attachLatestTaskToWaitingStory() {
        if (storyWaitingForTask == null) {
            return;
        }

        Task latestTask = Blackboard.getInstance().getLatestTask();

        if (latestTask == null) {
            return;
        }

        if (!storyWaitingForTask.getTasks().contains(latestTask)) {
            storyWaitingForTask.getTasks().add(latestTask);
        }

        storyWaitingForTask = null;

        refreshBoard();
        ViewsManager.getInstance().showPanel("SprintBoard");
    }

    private void moveTaskRight(Task task) {
        String status = task.getStatus();

        if (status.equals("NEW")) {
            task.setStatus("IN_PROGRESS");
        } else if (status.equals("IN_PROGRESS")) {
            task.setStatus("READY_FOR_TEST");
        } else if (status.equals("READY_FOR_TEST")) {
            task.setStatus("CLOSED");
        }

        Blackboard.getInstance().fireSprintChanged();
    }

    private void moveTaskLeft(Task task) {
        String status = task.getStatus();

        if (status.equals("CLOSED")) {
            task.setStatus("READY_FOR_TEST");
        } else if (status.equals("READY_FOR_TEST")) {
            task.setStatus("IN_PROGRESS");
        } else if (status.equals("IN_PROGRESS")) {
            task.setStatus("NEW");
        }

        Blackboard.getInstance().fireSprintChanged();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("tasks")) {
            attachLatestTaskToWaitingStory();
        }

        if (evt.getPropertyName().equals("sprints")) {
            refreshBoard();
        }
    }
}