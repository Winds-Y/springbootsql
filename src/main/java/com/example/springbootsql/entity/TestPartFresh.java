package com.example.springbootsql.entity;

public class TestPartFresh {
    private int id;

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

    private String name;

    public TestPartFresh() {
    }

    public TestPartFresh(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
