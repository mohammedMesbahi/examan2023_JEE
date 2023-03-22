package com.example.examan2023.dao;

import com.example.examan2023.beans.Task;

import java.util.List;

public interface TaskDao {
    Task add(Task task);
    Task delete(String id);
    Task update(Task task);
    Task get(String id);
    List<Task> getAll();
    List<Task> getAll(String userId);
    void swapToLeft(String id);
    void swapToRight(String id);
}
