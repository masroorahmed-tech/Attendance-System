package ui;

import model.Student;
import service.StudentService;
import javax.swing.*;
import java.awt.*;

/**
 * RegisterStudentDialog Class
 * Dialog for registering new students.
 * Demonstrates COMPOSITION: uses StudentService for business logic.
 */
public class RegisterStudentDialog extends JDialog {

    private JTextField cmsField;
    private JTextField nameField;
    private JComboBox<String> semesterBox;
    private JComboBox<String> sectionBox;

    public RegisterStudentDialog(JFrame parent) {
        super(parent, "Register New Student", true);
        setSize(380, 300);
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(30, 30, 30));

        initializeUI();
    }

    /**
     * Initialize dialog UI components.
     */
    private void initializeUI() {
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(7, 12, 7, 12);

        // CMS ID
        g.gridx = 0; g.gridy = 0;
        add(UIHelper.createLabel("CMS ID:"), g);
        g.gridx = 1;
        cmsField = new JTextField(14);
        add(cmsField, g);

        // Name
        g.gridx = 0; g.gridy = 1;
        add(UIHelper.createLabel("Name:"), g);
        g.gridx = 1;
        nameField = new JTextField(14);
        add(nameField, g);

        // Semester
        g.gridx = 0; g.gridy = 2;
        add(UIHelper.createLabel("Semester:"), g);
        g.gridx = 1;
        semesterBox = new JComboBox<>(new String[]{"CS-II", "CS-III", "CS-IV"});
        add(semesterBox, g);

        // Section
        g.gridx = 0; g.gridy = 3;
        add(UIHelper.createLabel("Section:"), g);
        g.gridx = 1;
        sectionBox = new JComboBox<>(new String[]{"Sec A", "Sec B", "Sec C"});
        add(sectionBox, g);

        // Submit button
        g.gridx = 0; g.gridy = 4; g.gridwidth = 2;
        JButton submitBtn = UIHelper.createGoldButton("Register");
        add(submitBtn, g);

        submitBtn.addActionListener(e -> handleRegistration());
    }

    /**
     * Handle student registration.
     */
    private void handleRegistration() {
        String cms  = cmsField.getText().trim();
        String name = nameField.getText().trim();
        String semester = (String) semesterBox.getSelectedItem();
        String section = (String) sectionBox.getSelectedItem();

        if (cms.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "CMS ID and Name cannot be empty.", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Create student object
        Student student = new Student(cms, name, semester, section);

        // Save to database using service layer
        if (StudentService.registerStudent(student)) {
            JOptionPane.showMessageDialog(this, 
                "Student \"" + name + "\" registered successfully!");
            dispose();
        }
    }
}
