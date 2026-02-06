package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService();



    public static void main(String[] args) {

            boolean mainRunning = true;

            while (mainRunning) {
                printMainMenu();
                int mainChoice = readInt();

                switch (mainChoice){
                    case 1 -> studentManagement();
                    case 2 -> courseManagement();
                    case 3 -> enrollmentManagement();
                    case 0 -> {
                        mainRunning = false;
                        System.out.println("Exiting LearnTrack. Goodbye!");
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }

            }


    }

    // Main menu
    private static void printMainMenu() {
        System.out.println("\n===== LearnTrack Main Menu =====");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Enrollment Management");
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

    // Student management methods
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

    private static void printStudentMenu() {
        System.out.println("\n===== LearnTrack Student Menu =====");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Find Student by ID");
        System.out.println("4. Deactivate Student");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");
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

    // Course management methods
    private static void courseManagement() {
        boolean running = true;

        while (running) {
            printCourseMenu();
            int choice = readInt();

            switch (choice) {
                case 1 -> addCourse();
                case 2 -> viewAllCourses();
                case 3 -> deactivateCourse();
                case 0 -> {
                    running = false;
                    System.out.println("Returning to Main Menu...");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void printCourseMenu() {
        System.out.println("\n===== LearnTrack Course Menu =====");
        System.out.println("1. Add Course");
        System.out.println("2. View All Courses");
        System.out.println("3. Deactivate Course");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");
    }

    private static void addCourse() {
        try {
            System.out.print("Course Name: ");
            String name = scanner.nextLine();

            if (!InputValidator.isValidString(name)) {
                throw new InvalidInputException("Course name cannot be empty.");
            }

            System.out.print("Description: ");
            String description = scanner.nextLine();

            System.out.print("Duration (weeks): ");
            int duration = readInt();

            if (duration <= 0) {
                throw new InvalidInputException("Duration must be positive.");
            }

            Course course = new Course(
                    IdGenerator.getNextCourseId(),
                    name,
                    description,
                    duration
            );

            courseService.addCourse(course);
            System.out.println("Course added successfully.");

        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void viewAllCourses() {
        var courses = courseService.getAllCourses();

        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        for (Course course : courses) {
            System.out.println(course.getCourseName() + " (" + course.getDurationInWeeks() + " weeks)" + (course.isActive() ? " [Active]" : " [Inactive]"));
        }
    }

    private static void deactivateCourse() {
        System.out.print("Enter course ID: ");
        int id = readInt();

        try {
            courseService.deactivateCourse(id);
            System.out.println("Course deactivated successfully.");
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    //enrollment management methods
    private static void enrollmentManagement() {
        boolean running = true;

        while (running) {
            printEnrollmentMenu();
            int choice = readInt();

            switch (choice) {
                case 1 -> enrollStudent();
                case 2 -> viewEnrollmentsByStudent();
                case 3 -> updateEnrollmentStatus();
                case 0 -> {
                    running = false;
                    System.out.println("Returning to Main Menu...");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }


    private static void printEnrollmentMenu() {
        System.out.println("\n===== LearnTrack Enrollment Menu =====");
        System.out.println("1. Enroll Student in Course");
        System.out.println("2. View Enrollments by Student");
        System.out.println("3. Update Enrollment Status");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");
    }

    private static void enrollStudent() {
        try {
            System.out.print("Student ID: ");
            int studentId = readInt();

            studentService.getStudentById(studentId); // validate student exists

            System.out.print("Course ID: ");
            int courseId = readInt();

            courseService.getCourseById(courseId); // validate course exists

            enrollmentService.enrollStudent(
                    studentId,
                    courseId,
                    IdGenerator.getNextEnrollmentId()
            );

            System.out.println("Student enrolled successfully.");

        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void viewEnrollmentsByStudent() {
        System.out.print("Student ID: ");
        int studentId = readInt();

        try {
            var enrollments =
                    enrollmentService.getEnrollmentsByStudentId(studentId);

            for (Enrollment e : enrollments) {
                System.out.println(
                        "Enrollment ID: " + e.getId() +
                                ", Course ID: " + e.getCourseId() +
                                ", Status: " + e.getStatus()
                );
            }

        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void updateEnrollmentStatus() {
        System.out.print("Enrollment ID: ");
        int enrollmentId = readInt();

        System.out.print("New Status (ACTIVE/COMPLETED/CANCELLED): ");
        String status = scanner.nextLine();

        try {
            enrollmentService.updateEnrollmentStatus(enrollmentId, status);
            System.out.println("Enrollment status updated.");
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }



}
