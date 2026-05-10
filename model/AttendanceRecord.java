package model;

/**
 * AttendanceRecord Entity Class
 * Represents an attendance record for a student on a specific date.
 * Demonstrates ENCAPSULATION with proper data validation.
 */
public class AttendanceRecord {
    private int id;
    private String cmsId;
    private String dateMark;
    private boolean isPresent;

    // Constructor (with ID - for DB records)
    public AttendanceRecord(int id, String cmsId, String dateMark, boolean isPresent) {
        this.id = id;
        this.cmsId = cmsId;
        this.dateMark = dateMark;
        this.isPresent = isPresent;
    }

    // Constructor (without ID - for new records)
    public AttendanceRecord(String cmsId, String dateMark, boolean isPresent) {
        this.cmsId = cmsId;
        this.dateMark = dateMark;
        this.isPresent = isPresent;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getCmsId() {
        return cmsId;
    }

    public String getDateMark() {
        return dateMark;
    }

    public boolean isPresent() {
        return isPresent;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    @Override
    public String toString() {
        return "AttendanceRecord{" +
                "id=" + id +
                ", cmsId='" + cmsId + '\'' +
                ", dateMark='" + dateMark + '\'' +
                ", isPresent=" + isPresent +
                '}';
    }
}
