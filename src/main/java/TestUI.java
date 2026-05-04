import javax.swing.*;
import java.awt.*;

public class TestUI extends JPanel {

    public TestUI() {
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Test Panel", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        JButton backBtn = new JButton("Back to Projects");
        backBtn.addActionListener(e -> ViewsManager.getInstance().showPanel("Project"));

        add(label, BorderLayout.CENTER);
        add(backBtn, BorderLayout.SOUTH);
    }

}