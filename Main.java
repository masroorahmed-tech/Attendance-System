import ui.*;
import javax.swing.*;

/**
 * Student Attendance Portal
 * Java Swing + MySQL with OOP Architecture
 *
 * ── HOW TO COMPILE & RUN (cmd) ────────────────────────────────────────────
 *   javac -cp ".;mysql-connector-j-9.7.0.jar" Main.java ui/*.java model/*.java service/*.java util/*.java
 *   java -cp ".;mysql-connector-j-9.7.0.jar" Main
 *
 * ── REQUIRED SQL SETUP ─────────────────────────────────────────────────────
 *   CREATE DATABASE IF NOT EXISTS attendance_sheet;
 *   USE attendance_sheet;
 *
 *   CREATE TABLE IF NOT EXISTS students (
 *       cms_id   VARCHAR(20)  PRIMARY KEY,
 *       name     VARCHAR(100) NOT NULL,
 *       semester VARCHAR(20),
 *       section  VARCHAR(20)
 *   );
 *
 *   CREATE TABLE IF NOT EXISTS attendance (
 *       id         INT AUTO_INCREMENT PRIMARY KEY,
 *       cms_id     VARCHAR(20),
 *       date_mark  VARCHAR(15),
 *       is_present BOOLEAN DEFAULT TRUE,
 *       FOREIGN KEY (cms_id) REFERENCES students(cms_id)
 *   );
 *
 * ── DEFAULT ADMIN LOGIN ────────────────────────────────────────────────────
 *   CMS: 1234   Password: password
 * ───────────────────────────────────────────────────────────────────────────
 *
 * ARCHITECTURE:
 * - Model Layer: Student.java, AttendanceRecord.java (entity classes)
 * - Service Layer: StudentService.java, AttendanceService.java (business logic)
 * - UI Layer: LoginFrame.java, PortalFrame.java, AttendanceTableFrame.java (presentation)
 * - Utilities: DatabaseConnection.java (data access), UIHelper.java (UI utilities)
 *
 * KEY OOP PRINCIPLES DEMONSTRATED:
 * 1. ENCAPSULATION: Private fields with public getters/setters in entity classes
 * 2. ABSTRACTION: DatabaseConnection hides database complexity
 * 3. SEPARATION OF CONCERNS: UI, business logic, and data access separated
 * 4. SINGLE RESPONSIBILITY: Each class has one clear purpose
 * 5. DRY PRINCIPLE: UIHelper eliminates UI code duplication
 * 6. COMPOSITION: Services composed within UI components
 */
public class Main {

    private LoginFrame loginFrame;
    private PortalFrame portalFrame;

    /**
     * Application entry point.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().start());
    }

    /**
     * Initialize and start the application.
     */
    private void start() {
        loginFrame = new LoginFrame();
        setupLoginFrameCallbacks();
        loginFrame.setVisible(true);
    }

    /**
     * Setup login frame event callbacks.
     */
    private void setupLoginFrameCallbacks() {
        loginFrame.setOnLoginSuccess(() -> {
            loginFrame.setVisible(false);
            showPortal();
        });

        loginFrame.setOnRegisterClick(() -> {
            RegisterStudentDialog dialog = new RegisterStudentDialog(loginFrame);
            dialog.setVisible(true);
        });
    }

    /**
     * Show the portal (semester/section/date selection).
     */
    private void showPortal() {
        portalFrame = new PortalFrame();
        
        portalFrame.setOnLogout(() -> {
            portalFrame.dispose();
            loginFrame.setVisible(true);
        });

        portalFrame.setOnCreateAttendance(params -> {
            String semester = params[0];
            String section = params[1];
            String date = params[2];

            portalFrame.setVisible(false);
            showAttendanceTable(semester, section, date);
        });

        portalFrame.setVisible(true);
    }

    /**
     * Show the attendance table for marking presence.
     */
    private void showAttendanceTable(String semester, String section, String date) {
        AttendanceTableFrame tableFrame = new AttendanceTableFrame(semester, section, date);

        tableFrame.setOnBack(() -> {
            portalFrame.setVisible(true);
        });

        tableFrame.setOnLogout(() -> {
            loginFrame.setVisible(true);
            JOptionPane.showMessageDialog(null, "Logged out successfully.");
        });

        tableFrame.setVisible(true);
    }
}
