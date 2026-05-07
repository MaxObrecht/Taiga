/**

 * This class is doing the following ...
 *
 * Handles the observer logic SprintUI for creating a sprint
 *
 * @author Max Obrecht
 * @version 1.0
 *
 */
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SprintUI extends JPanel implements PropertyChangeListener {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private SprintController controller = new SprintController();

    DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd MMMM uuuu", Locale.ENGLISH);


    private JTextField titleField = new JTextField(50);
    private LocalDate startDate = LocalDate.now();
    private JTextField startDateField = new JTextField(startDate.format(formatter));
    private LocalDate endDate = startDate.plusWeeks(2);
    private JTextField endDateField = new JTextField(endDate.format(formatter));

    public SprintUI() {
        Blackboard.getInstance().addObserver(this);
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel sprintPanel = new JPanel(new GridLayout(0, 2, 8, 8));
        sprintPanel.setBorder(BorderFactory.createTitledBorder("Add A Sprint"));

        sprintPanel.add(new JLabel("sprint name"));
        sprintPanel.add(titleField);

        sprintPanel.add(new JLabel("Start date"));
        sprintPanel.add(startDateField);

        sprintPanel.add(new JLabel("End date"));
        sprintPanel.add(endDateField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton createBtn = new JButton("Add A Sprint");
        createBtn.addActionListener(e -> createSprintNanny());
        buttonPanel.add(createBtn);

        JButton backBtn = new JButton("Back to User Stories");
        backBtn.addActionListener(e -> ViewsManager.getInstance().showPanel("UserStory"));
        buttonPanel.add(backBtn);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(sprintPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(bottomPanel, BorderLayout.NORTH);

    }

    private void createSprintNanny() {
        String title = titleField.getText().trim();

        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Sprint name cannot be empty.");
            return;
        }
        try {
            LocalDate startDate =
                    LocalDate.parse(startDateField.getText().trim(), formatter);

            LocalDate endDate =
                    LocalDate.parse(endDateField.getText().trim(), formatter);

            Sprint sprint = controller.createSprint(title, startDate.toString(), endDate.toString());
            controller.saveSprint(sprint);

            System.out.println("Created: " + sprint.getName());

            titleField.setText("");

            LocalDate today = LocalDate.now();
            startDateField.setText(today.format(formatter));
            endDateField.setText(today.plusWeeks(2).format(formatter));

            ViewsManager.getInstance().showPanel("UserStory");

        } catch (Exception e) {
            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + e.getMessage()
            );
        }
    }



    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("sprints")) {
            Blackboard.getInstance().getSprints();
        }

    }

}