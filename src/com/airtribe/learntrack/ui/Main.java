package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Person;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService studentService = new StudentService();


    public static void main(String[] args) {

            boolean running = true;

            while (running) {
                printMenu();
                int choice = readInt();

                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> viewAllStudents();
                    case 3 -> findStudentById();
                    case 4 -> deactivateStudent();
                    case 0 -> {
                        running = false;
                        System.out.println("Exiting LearnTrack. Goodbye!");
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            }
    }
    private static void printMenu() {
        System.out.println("\n===== LearnTrack Student Menu =====");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Find Student by ID");
        System.out.println("4. Deactivate Student");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    private static int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void addStudent() {
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Email (press Enter to skip): ");
        String email = scanner.nextLine();

        System.out.print("Batch: ");
        String batch = scanner.nextLine();

        Student student;
        if (email.isBlank()) {
            student = new Student(
                    IdGenerator.getNextStudentId(),
                    firstName,
                    lastName,
                    batch
            );
        } else {
            student = new Student(
                    IdGenerator.getNextStudentId(),
                    firstName,
                    lastName,
                    email,
                    batch
            );
        }

        studentService.addStudent(student);
        System.out.println("Student added successfully.");
    }

    private static void viewAllStudents() {
        if (studentService.getAllStudents().isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : studentService.getAllStudents()) {
            System.out.println(student.getDisplayName());
        }
    }

    private static void findStudentById() {
        System.out.print("Enter student ID: ");
        int id = readInt();

        try {
            Student student = studentService.getStudentById(id);
            System.out.println(student.getDisplayName());
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deactivateStudent() {
        System.out.print("Enter student ID: ");
        int id = readInt();

        try {
            studentService.deactivateStudent(id);
            System.out.println("Student deactivated successfully.");
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
