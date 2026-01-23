package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Person;
import com.airtribe.learntrack.entity.Student;

public class Main {
    public static void main(String[] args) {

        // Temporary test code – will be replaced by menu-driven UI

        System.out.println("LearnTrack started");

        Student s1 = new Student(1, "Aditya", "Tambe", "aditya@gmail.com", "A1");

        Person p1 = new Student(2, "Aniket", "Shinde", "B2");

        System.out.println(s1.getDisplayName());
        s1.setFirstName("Gaurav");
        s1.setLastName("Patil");
        System.out.println(s1.getDisplayName());
        System.out.println(p1.getDisplayName());

    }
}
