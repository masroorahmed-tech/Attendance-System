package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * DatabaseConnection Utility Class
 * Handles all database connectivity and provides a singleton pattern for connections.
 * Demonstrates ABSTRACTION: hides database details from other classes.
 */
public class DatabaseConnection {
    
    // Database credentials
    private static final String DB_URL      = "jdbc:mysql://localhost:3306/attendance_sheet";
    private static final String DB_USER     = "root";
    private static final String DB_PASSWORD = "Masr00r";

    // Load MySQL JDBC driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                "MySQL JDBC driver not found. Please ensure mysql-connector-j.jar is on the classpath.",
                "Driver Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Get a new database connection.
     * @return Connection object or null if connection fails
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Database Connection Error:\n" + e.getMessage(),
                "DB Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * Close a database connection safely.
     * @param conn Connection to close
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
