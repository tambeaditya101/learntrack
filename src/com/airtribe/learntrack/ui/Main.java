package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService studentService = new StudentService();


    public static void main(String[] args) {

            boolean mainRunning = true;

            while (mainRunning) {
                printMainMenu();
                int mainChoice = readInt();

                switch (mainChoice){
                    case 1 -> studentManagement();
                    case 2 -> System.out.println("Course Management - Coming Soon!");
                    case 3 -> System.out.println("Enrollment Management - Coming Soon!");
                    case 0 -> {
                        mainRunning = false;
                        System.out.println("Exiting LearnTrack. Goodbye!");
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }

            }


    }

    private static void studentManagement() {
        boolean studentMenuRunning = true;
        while (studentMenuRunning) {
            printStudentMenu();
            int choice = readInt();
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewAllStudents();
                case 3 -> findStudentById();
                case 4 -> deactivateStudent();
                case 0 -> {
                    studentMenuRunning = false;
                    System.out.println("Returning to Main Menu...");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }}


    private static void printMainMenu() {
        System.out.println("\n===== LearnTrack Main Menu =====");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Enrollment Management");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    private static void printStudentMenu() {
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
        try{
            System.out.print("First Name: ");
            String firstName = scanner.nextLine();

            if(!InputValidator.isValidString(firstName)){
                throw new InvalidInputException("First name cannot be empty.");
            }

            System.out.print("Last Name: ");
            String lastName = scanner.nextLine();

            if (!InputValidator.isValidString(lastName)) {
                throw new InvalidInputException("Last name cannot be empty.");
            }

            System.out.print("Email (press Enter to skip): ");
            String email = scanner.nextLine();

            System.out.print("Batch: ");
            String batch = scanner.nextLine();

            if (!InputValidator.isValidString(batch)) {
                throw new InvalidInputException("Batch cannot be empty.");
            }

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
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void viewAllStudents() {
        var students = studentService.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {
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
