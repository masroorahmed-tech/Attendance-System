package service;

import model.AttendanceRecord;
import util.DatabaseConnection;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * AttendanceService Class
 * Handles all attendance-related database operations.
 * Demonstrates SINGLE RESPONSIBILITY PRINCIPLE: focused only on attendance logic.
 */
public class AttendanceService {

    /**
     * Record attendance for a student.
     * @param cmsId Student's CMS ID
     * @param dateMark Date of attendance
     * @param isPresent Presence status
     * @return true if successful, false otherwise
     */
    public static boolean recordAttendance(String cmsId, String dateMark, boolean isPresent) {
        String sql = "INSERT INTO attendance (cms_id, date_mark, is_present) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn == null) return false;

            stmt.setString(1, cmsId);
            stmt.setString(2, dateMark);
            stmt.setBoolean(3, isPresent);
            
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Error recording attendance:\n" + e.getMessage(),
                "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Get all attendance records for a student.
     * @param cmsId Student's CMS ID
     * @return List of AttendanceRecord objects
     */
    public static List<AttendanceRecord> getAttendanceByStudent(String cmsId) {
        List<AttendanceRecord> records = new ArrayList<>();
        String sql = "SELECT id, cms_id, date_mark, is_present FROM attendance WHERE cms_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn == null) return records;

            stmt.setString(1, cmsId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                AttendanceRecord record = new AttendanceRecord(
                    rs.getInt("id"),
                    rs.getString("cms_id"),
                    rs.getString("date_mark"),
                    rs.getBoolean("is_present")
                );
                records.add(record);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return records;
    }

    /**
     * Get all attendance records for a given date.
     * @param dateMark Date to query
     * @return List of AttendanceRecord objects
     */
    public static List<AttendanceRecord> getAttendanceByDate(String dateMark) {
        List<AttendanceRecord> records = new ArrayList<>();
        String sql = "SELECT id, cms_id, date_mark, is_present FROM attendance WHERE date_mark = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn == null) return records;

            stmt.setString(1, dateMark);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                AttendanceRecord record = new AttendanceRecord(
                    rs.getInt("id"),
                    rs.getString("cms_id"),
                    rs.getString("date_mark"),
                    rs.getBoolean("is_present")
                );
                records.add(record);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return records;
    }
}
