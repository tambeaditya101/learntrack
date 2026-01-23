package com.airtribe.learntrack.entity;

public class Enrollment {

    private int id;
    private int studentId;
    private int courseId;
    private String enrollmentDate;
    private String status;

    public Enrollment(int id, int studentId, int courseId, String enrollmentDate, String status) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    // getters
    public int getId() {
        return id;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public String getStatus() {
        return status;
    }

    // setters
    public void setStatus(String status) {
        this.status = status;
    }
}
