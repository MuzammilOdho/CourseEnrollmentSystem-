package org.example.entity;

public class Student {
    private int id ;
    private String name ;
    private int userId;

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, int userId) {
        this.name = name;
        this.userId = userId;
    }
    public Student(int id, int userId,String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
}
