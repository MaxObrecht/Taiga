import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**

  * This class setups the UI for logining in
  *
  * @author Tyler Page
  * @version 1.0
  *
**/

public class LoginUI extends JPanel{
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private JTextField nameField = new JTextField(12);
    private JTextField passField = new JTextField(12);
    private JTextField createNameField = new JTextField(12);
    private JTextField createPassField = new JTextField(12);

    LoginStorage storage = new LoginStorage();

    public LoginUI(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 2, 2));
        formPanel.setBorder(BorderFactory.createTitledBorder("Create Task"));

        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(e -> 
            {
                if (storage.validateLogin(nameField.getText().trim(), passField.getText().trim())) {
                    cardLayout.show(mainPanel, "Project");
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Login");
                }
            }
        );

        JButton addUserBtn = new JButton("Create Login");
        addUserBtn.addActionListener(e -> createAccount());

        formPanel.add(new JLabel("Username:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(passField);

        formPanel.add(loginBtn);
        formPanel.add(addUserBtn);

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.add(formPanel);

        add(wrapper, BorderLayout.CENTER);
    }

    private void createAccount() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        panel.add(new JLabel("New Username:"));
        panel.add(createNameField);
        panel.add(new JLabel("New Password:"));
        panel.add(createPassField);

        int result = JOptionPane.showConfirmDialog(
            this,
            panel,
            "Create Account",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String name = createNameField.getText().trim();
            String pass = createPassField.getText().trim();

            if (name.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Empty Fields");
                return;
            }

            storage.addUser(name, pass);

            // Clear fields after use
            createNameField.setText("");
            createPassField.setText("");
        }
    }
}
