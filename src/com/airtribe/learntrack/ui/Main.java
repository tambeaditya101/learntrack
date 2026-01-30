package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Person;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.IdGenerator;

public class Main {
    public static void main(String[] args) {

        // Temporary test code – will be replaced by menu-driven UI

        System.out.println("LearnTrack started");

        Student s1 = new Student(IdGenerator.getNextStudentId(), "Aditya", "Tambe", "aditya@gmail.com", "A1");

        Person p1 = new Student(IdGenerator.getNextStudentId(), "Aniket", "Shinde", "B2");

        System.out.println(s1.getDisplayName());
        System.out.println(s1.getId() );
        s1.setFirstName("Gaurav");
        s1.setLastName("Patil");
        System.out.println(s1.getDisplayName());
        System.out.println(p1.getDisplayName());
        System.out.println(p1.getId() );

        StudentService studentService = new StudentService();

        // Using StudentService to addStudent.
        studentService.addStudent(s1);

        try{
            Student student = studentService.getStudentById(1);
            System.out.println(student.getDisplayName());
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }


    }
}
