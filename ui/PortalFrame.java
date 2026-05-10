package ui;

import javax.swing.*;
import java.awt.*;

/**
 * PortalFrame Class
 * Main portal for selecting semester, section, and date for attendance.
 * Demonstrates DELEGATION: passes control to other components.
 */
public class PortalFrame extends JFrame {

    private JComboBox<String> semesterBox;
    private JComboBox<String> sectionBox;
    private JComboBox<String> dayBox;
    private JComboBox<String> monthBox;
    private Runnable onLogout;
    private java.util.function.Consumer<String[]> onCreateAttendance;

    public PortalFrame() {
        setTitle("Attendance Portal – Select Session");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 420);
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

        JPanel card = new JPanel(new GridLayout(7, 1, 8, 6));
        card.setBackground(new Color(0, 0, 0, 170));
        card.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Info labels
        JPanel infoRow1 = UIHelper.createTransparentRow();
        infoRow1.add(UIHelper.createLabel("Logged in as: Admin   (CMS: 1234)"));
        card.add(infoRow1);

        JPanel infoRow2 = UIHelper.createTransparentRow();
        infoRow2.add(UIHelper.createLabel("Course: OOP  |  Term: Spring 2026"));
        card.add(infoRow2);

        // Semester dropdown
        semesterBox = new JComboBox<>(new String[]{"CS-II", "CS-III", "CS-IV"});
        card.add(UIHelper.createLabeledRow("Semester:", semesterBox));

        // Section dropdown
        sectionBox = new JComboBox<>(new String[]{"Sec A", "Sec B", "Sec C"});
        card.add(UIHelper.createLabeledRow("Section:", sectionBox));

        // Date picker
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) days[i] = String.valueOf(i + 1);
        dayBox = new JComboBox<>(days);
        
        monthBox = new JComboBox<>(
            new String[]{"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"}
        );

        JPanel dateRow = UIHelper.createTransparentRow();
        dateRow.add(UIHelper.createLabel("Date (DD/MM):"));
        dateRow.add(dayBox);
        dateRow.add(UIHelper.createLabel(" / "));
        dateRow.add(monthBox);
        card.add(dateRow);

        // Buttons
        JButton createBtn = UIHelper.createGoldButton("CREATE ATTENDANCE");
        JButton logoutBtn = UIHelper.createGoldButton("LOGOUT");
        JPanel btnRow = UIHelper.createTransparentRow();
        btnRow.add(createBtn);
        btnRow.add(Box.createHorizontalStrut(12));
        btnRow.add(logoutBtn);
        card.add(btnRow);

        bg.add(card);
        setContentPane(bg);

        // Event handlers
        createBtn.addActionListener(e -> handleCreateAttendance());
        logoutBtn.addActionListener(e -> {
            if (onLogout != null) onLogout.run();
        });
    }

    /**
     * Handle attendance creation request.
     */
    private void handleCreateAttendance() {
        String semester = (String) semesterBox.getSelectedItem();
        String section = (String) sectionBox.getSelectedItem();
        String day = (String) dayBox.getSelectedItem();
        String month = (String) monthBox.getSelectedItem();

        if (onCreateAttendance != null) {
            onCreateAttendance.accept(new String[]{semester, section, day + " " + month});
        }
    }

    /**
     * Set callback for logout.
     */
    public void setOnLogout(Runnable callback) {
        this.onLogout = callback;
    }

    /**
     * Set callback for create attendance. Receives [semester, section, date].
     */
    public void setOnCreateAttendance(java.util.function.Consumer<String[]> callback) {
        this.onCreateAttendance = callback;
    }
}
