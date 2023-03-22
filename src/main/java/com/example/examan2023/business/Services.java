package com.example.examan2023.business;

import com.example.examan2023.beans.Task;

import java.util.List;

public interface Services {
    Task addTask(Task task);
    Task deleteTask(String id);
    Task updateTask(Task task);
    Task getTask(String id);
    List<Task> getAllTasks();
    List<Task> getAllTasks(String userId);
    void swapTaskToLeft(String id);
    void swapTaskToRight(String id);

}
