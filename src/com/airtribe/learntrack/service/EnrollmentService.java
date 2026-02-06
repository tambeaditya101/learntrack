package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentService {

    private List<Enrollment> enrollments = new ArrayList<>();

    public void enrollStudent(int studentId, int courseId, int enrollmentId) {
        Enrollment enrollment = new Enrollment(
                enrollmentId,
                studentId,
                courseId,
                "2024-01-01",   // simple placeholder
                "ACTIVE"
        );
        enrollments.add(enrollment);
    }

    public List<Enrollment> getEnrollmentsByStudentId(int studentId) {
        List<Enrollment> result = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == studentId) {
                result.add(enrollment);
            }
        }

        if (result.isEmpty()) {
            throw new EntityNotFoundException(
                    "No enrollments found for student id " + studentId
            );
        }

        return result;
    }

    public void updateEnrollmentStatus(int enrollmentId, String status) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId() == enrollmentId) {
                enrollment.setStatus(status);
                return;
            }
        }
        throw new EntityNotFoundException(
                "Enrollment with id " + enrollmentId + " not found"
        );
    }
}
