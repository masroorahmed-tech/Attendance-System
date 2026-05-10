-- ============================================================================
-- Student Attendance Portal - Database Setup Script
-- ============================================================================
-- Run this script in MySQL to set up the required database and tables
-- ============================================================================

-- Create database
CREATE DATABASE IF NOT EXISTS attendance_sheet;
USE attendance_sheet;

-- ============================================================================
-- Students Table
-- Stores student information (CMS ID, Name, Semester, Section)
-- ============================================================================
CREATE TABLE IF NOT EXISTS students (
    cms_id   VARCHAR(20)  PRIMARY KEY COMMENT 'Student CMS ID (unique identifier)',
    name     VARCHAR(100) NOT NULL COMMENT 'Student full name',
    semester VARCHAR(20) COMMENT 'Semester (CS-II, CS-III, CS-IV)',
    section  VARCHAR(20) COMMENT 'Section (Sec A, Sec B, Sec C)',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Record creation time'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci 
COMMENT='Stores registered student information';

-- ============================================================================
-- Attendance Table
-- Stores attendance records for each student on each date
-- ============================================================================
CREATE TABLE IF NOT EXISTS attendance (
    id         INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Unique attendance record ID',
    cms_id     VARCHAR(20) NOT NULL COMMENT 'Student CMS ID (Foreign Key)',
    date_mark  VARCHAR(15) COMMENT 'Date of attendance (DD MMM format)',
    is_present BOOLEAN DEFAULT TRUE COMMENT 'Presence status (1=Present, 0=Absent)',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Record creation time',
    
    -- Foreign key constraint to ensure referential integrity
    CONSTRAINT fk_attendance_student 
        FOREIGN KEY (cms_id) 
        REFERENCES students(cms_id) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci 
COMMENT='Stores attendance records for students';

-- Create index on cms_id in attendance table for faster queries
CREATE INDEX idx_attendance_cms_id ON attendance(cms_id);
CREATE INDEX idx_attendance_date ON attendance(date_mark);

-- ============================================================================
-- OPTIONAL: Insert Sample Data (for testing)
-- ============================================================================
-- Uncomment below to insert sample students for testing

/*
INSERT INTO students (cms_id, name, semester, section) VALUES
('100', 'Ali Khan', 'CS-II', 'Sec A'),
('101', 'Fatima Ahmed', 'CS-II', 'Sec A'),
('102', 'Hassan Raza', 'CS-III', 'Sec B'),
('103', 'Zainab Ali', 'CS-III', 'Sec B'),
('104', 'Muhammad Hasan', 'CS-IV', 'Sec C');

INSERT INTO attendance (cms_id, date_mark, is_present) VALUES
('100', '5 MAY', 1),
('101', '5 MAY', 1),
('102', '5 MAY', 0),
('103', '5 MAY', 1);
*/

-- ============================================================================
-- Verification Queries
-- ============================================================================
-- Run these to verify the setup

-- Check students table
-- SELECT COUNT(*) as total_students FROM students;

-- Check attendance records
-- SELECT COUNT(*) as total_records FROM attendance;

-- Check all tables exist
-- SHOW TABLES;

-- ============================================================================
-- End of Setup Script
-- ============================================================================
