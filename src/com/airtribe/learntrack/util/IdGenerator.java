package com.airtribe.learntrack.util;

public class IdGenerator {
    private static int studentIdCounter = 1;
    private static int courseIdCounter = 1;
    private static int enrollmentIdCounter = 1;

    // Student ID
    public static int getNextStudentId() {
        return studentIdCounter++;
    }

    // Course ID
    public static int getNextCourseId() {
        return courseIdCounter++;
    }

    // Enrollment ID
    public static int getNextEnrollmentId() {
        return enrollmentIdCounter++;
    }
}
