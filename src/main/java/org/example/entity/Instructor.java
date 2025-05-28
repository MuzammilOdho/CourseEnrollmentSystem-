package org.example.entity;

public class Instructor {
    private int id ;
    private String name ;
    private int userId;

    public Instructor(String name , int userId) {
        this.name = name;
        this.userId = userId;
    }

    public Instructor(int id, String name, int userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }
}

