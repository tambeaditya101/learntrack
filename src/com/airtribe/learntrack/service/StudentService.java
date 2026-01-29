package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student){
        students.add(student);
    }

    public List<Student> getAllStudents(){
        return  students;
    }

    public Student getStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public boolean updateStudent(int id, String firstName, String lastName, String batch) {
        Student student = getStudentById(id);

        if (student == null) {
            return false;
        }

        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setBatch(batch);
        return true;
    }

    public boolean deactivateStudent(int id) {
        Student student = getStudentById(id);

        if (student == null) {
            return false;
        }

        student.deactivate();
        return true;
    }


}
