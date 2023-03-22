package com.example.examan2023.business;

import com.example.examan2023.beans.Task;
import com.example.examan2023.dao.TaskDao;
import com.example.examan2023.dao.TaskDoaMemory;

import java.util.List;

public class DefaultServices implements Services {
    private TaskDao taskDoa;
    private static DefaultServices instance;

    private DefaultServices(TaskDao taskDoa) {
        this.taskDoa = taskDoa;
    }
    public static DefaultServices getInstance() {
        if (instance == null) {
            instance = new DefaultServices(new TaskDoaMemory());
        }
        return instance;
    }
    @Override
    public Task addTask(Task task) {
        return taskDoa.add(task);
    }

    @Override
    public Task deleteTask(String id) {
        return taskDoa.delete(id);
    }

    @Override
    public Task updateTask(Task task) {
        return taskDoa.update(task);
    }

    @Override
    public Task getTask(String id) {
        return taskDoa.get(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskDoa.getAll();
    }

    @Override
    public List<Task> getAllTasks(String userId) {
        return taskDoa.getAll(userId);
    }

    @Override
    public void swapTaskToLeft(String id) {
        taskDoa.swapToLeft(id);
    }

    @Override
    public void swapTaskToRight(String id) {
        taskDoa.swapToRight(id);
    }
}
