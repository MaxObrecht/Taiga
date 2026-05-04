import javax.swing.*;
import java.awt.*;

public class ViewsManager {

    private static ViewsManager instance;
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private ViewsManager() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        frame = new JFrame("Taiga");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);
    }

    public static ViewsManager getInstance() {
        if (instance == null) {
            instance = new ViewsManager();
        }
        return instance;
    }

    public void addPanel(JPanel panel, String name) {
        mainPanel.add(panel, name);
    }

    public void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }

    public void start(String initialPanel) {
        frame.setVisible(true);
        cardLayout.show(mainPanel, initialPanel);
    }

}