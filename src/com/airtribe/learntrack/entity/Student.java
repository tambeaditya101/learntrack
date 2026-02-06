package com.airtribe.learntrack.entity;

public class Student extends Person {

    private String batch;
    private boolean active;

    public Student(int id, String firstName, String lastName, String email, String batch){
        super(id,firstName,lastName,email);
        this.batch = batch;
        this.active = true;
    }

    public Student(int id, String firstName, String lastName, String batch) {
        super(id, firstName, lastName, null);
        this.batch = batch;
        this.active = true;
    }


    // getters
    public String getBatch() {
        return batch;
    }

    public boolean isActive() {
        return active;
    }

    // setters
    public void setBatch(String batch) {
        this.batch = batch;
    }

    public void deactivate() {
        this.active = false;
    }

    @Override
    public String getDisplayName() {
        return "Student: " + super.getDisplayName() + " | Batch: " + batch + " | Status: " + (active ? "Active" : "Inactive");
    }

}
