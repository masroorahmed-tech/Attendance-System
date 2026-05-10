package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * LoginFrame Class
 * Handles teacher/admin login authentication.
 * Demonstrates SINGLE RESPONSIBILITY: focused only on login UI.
 */
public class LoginFrame extends JFrame {
    
    private static final String ADMIN_CMS  = "1234";
    private static final String ADMIN_PASS = "password";
    
    private JTextField cmsField;
    private JPasswordField passField;
    private Runnable onLoginSuccess;
    private Runnable onRegisterClick;

    public LoginFrame() {
        setTitle("Attendance Portal – Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        ImageIcon icon = UIHelper.loadIcon("logo.png");
        if (icon != null) setIconImage(icon.getImage());

        initializeUI();
    }

    /**
     * Initialize UI components.
     */
    private void initializeUI() {
        JPanel bg = UIHelper.createBackgroundPanel();
        bg.setLayout(new GridBagLayout());

        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(new Color(0, 0, 0, 170));
        card.setBorder(BorderFactory.createEmptyBorder(28, 36, 28, 36));

        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(7, 6, 7, 6);

        // Title
        g.gridx = 0; g.gridy = 0; g.gridwidth = 2;
        JLabel title = new JLabel("Attendance Portal", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(255, 215, 0));
        card.add(title, g);

        // CMS field
        g.gridy = 1; g.gridwidth = 1;
        card.add(UIHelper.createLabel("CMS ID:"), g);
        g.gridx = 1;
        cmsField = new JTextField(14);
        card.add(cmsField, g);

        // Password field
        g.gridy = 2; g.gridx = 0;
        card.add(UIHelper.createLabel("Password:"), g);
        g.gridx = 1;
        passField = new JPasswordField(14);
        card.add(passField, g);

        // Buttons
        g.gridy = 3; g.gridx = 0; g.gridwidth = 1;
        JButton loginBtn = UIHelper.createGoldButton("LOGIN");
        card.add(loginBtn, g);

        g.gridx = 1;
        JButton regBtn = UIHelper.createGoldButton("Register Student");
        card.add(regBtn, g);

        bg.add(card);
        setContentPane(bg);

        // Event handlers
        loginBtn.addActionListener(e -> handleLogin());
        regBtn.addActionListener(e -> {
            if (onRegisterClick != null) onRegisterClick.run();
        });

        passField.addActionListener(e -> loginBtn.doClick());
    }

    /**
     * Handle login validation.
     */
    private void handleLogin() {
        String cms = cmsField.getText().trim();
        char[] pw  = passField.getPassword();

        if (cms.equals(ADMIN_CMS) && Arrays.equals(pw, ADMIN_PASS.toCharArray())) {
            Arrays.fill(pw, '0');
            if (onLoginSuccess != null) {
                onLoginSuccess.run();
            }
        } else {
            Arrays.fill(pw, '0');
            JOptionPane.showMessageDialog(this,
                "Wrong CMS or Password.\n(Default: CMS = 1234 | Password = password)",
                "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Set callback for successful login.
     */
    public void setOnLoginSuccess(Runnable callback) {
        this.onLoginSuccess = callback;
    }

    /**
     * Set callback for register button click.
     */
    public void setOnRegisterClick(Runnable callback) {
        this.onRegisterClick = callback;
    }
}
