package com.example.examan2023.beans;

import java.util.Random;

public class Task {
    String id;
    String title;
    String owner;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", owner='" + owner + '\'' +
                '}';

    }

    public Task(String title, String owner) {
        super();
        Random r =new Random();
        this.id = r.nextInt(1000000)+"";
        this.title = title;
        this.owner = owner;
    }
    public Task() {
        super();
        Random r =new Random();
        this.id = r.nextInt(1000000)+"";
    };
}
