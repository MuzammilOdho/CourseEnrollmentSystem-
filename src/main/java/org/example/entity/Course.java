package org.example.entity;

public class Course {
    private int id;
    private String name;
    private int capacity;
    private int instructorId;


    public Course(int id, String name, int capacity, int instructorId) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.instructorId = instructorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }
}
