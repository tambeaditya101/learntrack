package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;

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
//        return null;
        throw new EntityNotFoundException("Student with id " + id + " not found");

    }

    public void updateStudent(int id, String firstName, String lastName, String batch) {
        Student student = getStudentById(id);

        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setBatch(batch);
    }

    // method overloading
    public void updateStudent(int id, String firstName, String lastName) {
        Student student = getStudentById(id);

        student.setFirstName(firstName);
        student.setLastName(lastName);
    }

    public void deactivateStudent(int id) {
        Student student = getStudentById(id);
        student.deactivate();
    }


}
