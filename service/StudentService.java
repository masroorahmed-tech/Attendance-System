package service;

import model.Student;
import util.DatabaseConnection;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentService Class
 * Handles all student-related database operations.
 * Demonstrates SEPARATION OF CONCERNS: business logic separated from UI.
 * This is a SERVICE LAYER pattern for better code organization.
 */
public class StudentService {

    /**
     * Register a new student in the database.
     * @param student Student object to register
     * @return true if successful, false otherwise
     */
    public static boolean registerStudent(Student student) {
        String sql = "INSERT INTO students (cms_id, name, semester, section) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn == null) return false;

            stmt.setString(1, student.getCmsId());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getSemester());
            stmt.setString(4, student.getSection());
            
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Error registering student:\n" + e.getMessage(),
                "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Load all students for a given semester and section.
     * @param semester Semester (e.g., "CS-II")
     * @param section Section (e.g., "Sec A")
     * @return List of Student objects
     */
    public static List<Student> getStudentsByClasses(String semester, String section) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT cms_id, name, semester, section FROM students WHERE semester = ? AND section = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn == null) return students;

            stmt.setString(1, semester);
            stmt.setString(2, section);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Student student = new Student(
                    rs.getString("cms_id"),
                    rs.getString("name"),
                    rs.getString("semester"),
                    rs.getString("section")
                );
                students.add(student);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Error loading students:\n" + e.getMessage(),
                "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return students;
    }

    /**
     * Get a student by CMS ID.
     * @param cmsId CMS ID of the student
     * @return Student object or null if not found
     */
    public static Student getStudentById(String cmsId) {
        String sql = "SELECT cms_id, name, semester, section FROM students WHERE cms_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn == null) return null;

            stmt.setString(1, cmsId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Student(
                    rs.getString("cms_id"),
                    rs.getString("name"),
                    rs.getString("semester"),
                    rs.getString("section")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
