import javax.swing.*;
import java.awt.*;

public class TestUI extends JPanel {

    public TestUI(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Test Panel", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        JButton backBtn = new JButton("Back to Projects");
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, "Project"));

        add(label, BorderLayout.CENTER);
        add(backBtn, BorderLayout.SOUTH);
    }

}