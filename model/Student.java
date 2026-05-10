package model;

/**
 * Student Entity Class
 * Represents a student with their core information.
 * Demonstrates ENCAPSULATION: private fields with public accessors.
 */
public class Student {
    private String cmsId;
    private String name;
    private String semester;
    private String section;

    // Constructor
    public Student(String cmsId, String name, String semester, String section) {
        this.cmsId = cmsId;
        this.name = name;
        this.semester = semester;
        this.section = section;
    }

    // Getters
    public String getCmsId() {
        return cmsId;
    }

    public String getName() {
        return name;
    }

    public String getSemester() {
        return semester;
    }

    public String getSection() {
        return section;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @Override
    public String toString() {
        return "Student{" +
                "cmsId='" + cmsId + '\'' +
                ", name='" + name + '\'' +
                ", semester='" + semester + '\'' +
                ", section='" + section + '\'' +
                '}';
    }
}
