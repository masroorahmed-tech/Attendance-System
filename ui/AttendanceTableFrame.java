package ui;

import model.AttendanceRecord;
import model.Student;
import service.AttendanceService;
import service.StudentService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.List;

/**
 * AttendanceTableFrame Class
 * Displays attendance table for marking student presence.
 * Demonstrates DATA BINDING: connects model data to UI table.
 */
public class AttendanceTableFrame extends JFrame {

    private DefaultTableModel tableModel;
    private JTable attendanceTable;
    private Runnable onBack;
    private Runnable onLogout;

    public AttendanceTableFrame(String semester, String section, String date) {
        setTitle("Attendance – " + section + " | " + semester + " | " + date);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(620, 420);
        setLocationRelativeTo(null);

        // Load students from database
        List<Student> students = StudentService.getStudentsByClasses(semester, section);

        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "No students found for " + semester + " / " + section + ".\nRegister students first.",
                "No Data", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            return;
        }

        initializeTable(students, semester, section, date);
        setupUI(date);
    }

    /**
     * Initialize the attendance table with student data.
     */
    private void initializeTable(List<Student> students, String semester, String section, String date) {
        String[] columns = {"CMS ID", "Name", "Semester", "Section", "Date", "Present"};
        Object[][] data = new Object[students.size()][6];

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            data[i][0] = student.getCmsId();
            data[i][1] = student.getName();
            data[i][2] = semester;
            data[i][3] = section;
            data[i][4] = date;
            data[i][5] = Boolean.TRUE;  // default: present
        }

        // Create custom table model with boolean column
        tableModel = new DefaultTableModel(data, columns) {
            @Override
            public Class<?> getColumnClass(int col) {
                return col == 5 ? Boolean.class : String.class;
            }
            @Override
            public boolean isCellEditable(int row, int col) {
                return col == 5;  // only Present checkbox is editable
            }
        };

        attendanceTable = new JTable(tableModel);
        attendanceTable.setRowHeight(28);
        attendanceTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        attendanceTable.setFont(new Font("Arial", Font.PLAIN, 13));

        // Configure checkbox column
        TableColumn checkCol = attendanceTable.getColumnModel().getColumn(5);
        checkCol.setCellRenderer(attendanceTable.getDefaultRenderer(Boolean.class));
        checkCol.setCellEditor(attendanceTable.getDefaultEditor(Boolean.class));
    }

    /**
     * Setup UI layout and buttons.
     */
    private void setupUI(String date) {
        JScrollPane scroll = new JScrollPane(attendanceTable);

        // Buttons
        JButton saveBtn = UIHelper.createGoldButton("SAVE ATTENDANCE");
        JButton backBtn = new JButton("<< Back");
        JButton logoutBtn = UIHelper.createGoldButton("LOGOUT");

        backBtn.setFont(new Font("Arial", Font.BOLD, 13));

        // Event handlers
        saveBtn.addActionListener(e -> handleSaveAttendance(date));
        backBtn.addActionListener(e -> {
            dispose();
            if (onBack != null) onBack.run();
        });
        logoutBtn.addActionListener(e -> {
            dispose();
            if (onLogout != null) onLogout.run();
        });

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 8));
        btnPanel.add(backBtn);
        btnPanel.add(saveBtn);
        btnPanel.add(logoutBtn);

        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }

    /**
     * Handle saving attendance records to database.
     */
    private void handleSaveAttendance(String date) {
        int saved = 0;
        int total = tableModel.getRowCount();

        for (int i = 0; i < total; i++) {
            String cmsId = (String) tableModel.getValueAt(i, 0);
            boolean isPresent = (Boolean) tableModel.getValueAt(i, 5);

            if (AttendanceService.recordAttendance(cmsId, date, isPresent)) {
                saved++;
            }
        }

        JOptionPane.showMessageDialog(this,
            saved + " / " + total + " attendance records saved successfully!");
    }

    /**
     * Set callback for back button.
     */
    public void setOnBack(Runnable callback) {
        this.onBack = callback;
    }

    /**
     * Set callback for logout button.
     */
    public void setOnLogout(Runnable callback) {
        this.onLogout = callback;
    }
}
