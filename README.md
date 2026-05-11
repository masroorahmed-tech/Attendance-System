# Student Attendance Portal

**A professional Java Swing + MySQL application for managing student attendance with proper OOP architecture.**

This project demonstrates core Object-Oriented Programming principles: **Encapsulation**, **Abstraction**, **Separation of Concerns**, **Single Responsibility Principle**, and **DRY** (Don't Repeat Yourself).

---

## 📋 Group Members

| Name | CMS ID | Section |
|------|--------|---------|
| Masroor Ahmed | 023-25-0163 | Sec C |
| Nitesh Metai| 023-25-0173 | Sec C |
| Krishna Rai| 023-25-0194 | Sec C |

---

## 🎯 Project Purpose

The **Student Attendance Portal** is a system designed to simplify and automate the attendance management process in educational institutions. Faculty/staff can:
- Register new students in the system
- Mark attendance for specific classes by date
- Store and retrieve attendance records persistently
- Filter students by semester and section

---

## ✨ Key Features

- **Secure Authentication:** Faculty/staff login with hardcoded admin credentials
- **Student Management:** Register and manage student records (CMS ID, Name, Semester, Section)
- **Attendance Tracking:** Mark attendance by date with checkbox interface
- **Database Integration:** Persistent storage using MySQL
- **Multi-tiered Architecture:** Proper separation of concerns (Model-Service-UI-Utility layers)
- **Professional UI:** Java Swing GUI with modern styling and background images

---

## 🏗️ Architecture & OOP Design

### Package Structure
```
model/
  ├── Student.java              # Entity class (Encapsulation: private fields + getters/setters)
  └── AttendanceRecord.java     # Entity class for attendance records

service/
  ├── StudentService.java       # Business logic (Single Responsibility)
  └── AttendanceService.java    # Attendance operations (CRUD)

ui/
  ├── LoginFrame.java           # Login screen
  ├── PortalFrame.java          # Main portal (semester/section selection)
  ├── AttendanceTableFrame.java # Attendance marking table
  ├── RegisterStudentDialog.java# Student registration dialog
  └── UIHelper.java             # Reusable UI components (DRY Principle)

util/
  └── DatabaseConnection.java   # Database abstraction layer

Main.java                        # Application entry point & orchestrator
```

### OOP Principles Demonstrated

1. **Encapsulation:** Private fields with public getters/setters in `Student.java` and `AttendanceRecord.java`
2. **Abstraction:** `DatabaseConnection` class hides MySQL complexity
3. **Separation of Concerns:** UI, business logic, and database access separated into different layers
4. **Single Responsibility:** Each class has one clear, focused purpose
5. **DRY Principle:** `UIHelper.java` eliminates UI code duplication (button styles, labels, etc.)
6. **Composition:** Services and utilities composed within UI components

---

## 🚀 Getting Started

### Prerequisites

- **Java Development Kit (JDK)** 8 or higher
- **MySQL Server** (running on `localhost:3306`)
- **MySQL JDBC Connector** (included: `mysql-connector-j-9.7.0.jar`)

### Step 1: Database Setup

Open your MySQL client and execute:

```sql
CREATE DATABASE IF NOT EXISTS attendance_sheet;
USE attendance_sheet;

CREATE TABLE IF NOT EXISTS students (
    cms_id   VARCHAR(20)  PRIMARY KEY,
    name     VARCHAR(100) NOT NULL,
    semester VARCHAR(20),
    section  VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS attendance (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    cms_id     VARCHAR(20),
    date_mark  VARCHAR(15),
    is_present BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (cms_id) REFERENCES students(cms_id)
);
```

### Step 2: Configuration

Update MySQL credentials in `util/DatabaseConnection.java`:
```java
private static final String DB_URL      = "jdbc:mysql://localhost:3306/attendance_sheet";
private static final String DB_USER     = "root";
private static final String DB_PASSWORD = ".......";  // Change to your password
```

### Step 3: Compilation

Navigate to the `Final_Project` directory and compile all classes:

**Windows (Command Prompt):**
```cmd
javac -cp ".;mysql-connector-j-9.7.0.jar" Main.java ui/*.java model/*.java service/*.java util/*.java
```

**Linux/Mac (Terminal):**
```bash
javac -cp ".:mysql-connector-j-9.7.0.jar" Main.java ui/*.java model/*.java service/*.java util/*.java
```

### Step 4: Execution

**Windows:**
```cmd
java -cp ".;mysql-connector-j-9.7.0.jar" Main
```

**Linux/Mac:**
```bash
java -cp ".:mysql-connector-j-9.7.0.jar" Main
```

---

## 📂 Project Structure

```
Final_Project/
├── Main.java                      # Application orchestrator & entry point
├── mysql-connector-j-9.7.0.jar   # MySQL JDBC driver
├── logo.png                       # Background image asset
│
├── model/
│   ├── Student.java              # Student entity with encapsulation
│   └── AttendanceRecord.java     # Attendance record entity
│
├── service/
│   ├── StudentService.java       # Database operations for students
│   └── AttendanceService.java    # Database operations for attendance
│
├── ui/
│   ├── LoginFrame.java           # Teacher/admin login screen
│   ├── PortalFrame.java          # Main portal for session selection
│   ├── AttendanceTableFrame.java # Table for marking attendance
│   ├── RegisterStudentDialog.java# Dialog for student registration
│   └── UIHelper.java             # Shared UI utilities
│
└── util/
    └── DatabaseConnection.java   # Database connection management
```

---

## 🔑 Default Credentials

| Field | Value |
|-------|-------|
| CMS ID | 1234 |
| Password | password |

---

## 📹 Demo Video

https://youtu.be/6XzPOzijEH0

---

## 📹 Github URL

https://github.com/masroorahmed-tech/Attendance-System.git

---

## 🔍 How It Works

1. **Login Screen:** Admin logs in with CMS: `1234`, Password: `password`
2. **Portal Screen:** Select semester (CS-II/III/IV), section (Sec A/B/C), and date
3. **Registration:** New students can be registered via the "Register Student" button
4. **Attendance Table:** Shows all students for selected class; mark present/absent with checkboxes
5. **Database Save:** Attendance data persists in MySQL database

---

## 📚 Technologies Used

- **Language:** Java (JDK 8+)
- **UI Framework:** Java Swing
- **Database:** MySQL
- **JDBC Driver:** mysql-connector-j-9.7.0.jar
- **Architecture:** Multi-layered (Model-Service-UI-Utility)

---

## 📝 Academic Notes

- **Code Quality:** Follows Java naming conventions and clean code principles
- **OOP Implementation:** Real usage of OOP concepts, not just buzzwords
- **Comments:** Well-documented with clear explanations of functionality
- **Exception Handling:** Proper error handling and user feedback
- **Database Design:** Proper schema with foreign keys and constraints

---

## 📜 License

MIT License - Free to use and modify

---

*Developed as part of the OOP Semester Project (Spring 2026)*
*Course: Object-Oriented Programming*
*Due: May 8, 2026*
