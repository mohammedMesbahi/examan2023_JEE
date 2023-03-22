package com.example.examan2023.dao;

import com.example.examan2023.beans.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDoaMemory implements TaskDao{
    List<Task> tasks;

    public TaskDoaMemory() {
        this.tasks = new ArrayList<>();
        add(new Task("title1", "owner1"));
        add(new Task("title2", "owner2"));
        add(new Task("title3", "owner3"));
    }

    @Override
    public Task add(Task task) {
        tasks.add(task);
        return task;
    }

    @Override
    public Task delete(String id) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                tasks.remove(task);
                return task;
            }
        }
        return null;
    }

    @Override
    public Task update(Task task) {
        for (Task t : tasks) {
            if (t.getId().equals(task.getId())) {
                t.setTitle(task.getTitle());
                return t;
            }
        }
        return null;
    }

    @Override
    public Task get(String id) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    @Override
    public List<Task> getAll() {
        return tasks;
    }

    @Override
    public List<Task> getAll(String userId) {
        List<Task> hisTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getOwner().equals(userId)) {
                hisTasks.add(task);
            }
        }
        return hisTasks;
    }

    @Override
    public void swapToLeft(String id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(id)) {
                Task temp = tasks.get(i);
                tasks.set(i,tasks.get((i +1 + tasks.size()) % tasks.size()));
                tasks.set((i +1 +tasks.size())% tasks.size(), temp);
                return;
            }
        }
    }

    @Override
    public void swapToRight(String id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(id)) {
                Task temp = tasks.get(i);
                tasks.set(i,tasks.get((i -1 + tasks.size()) % tasks.size()));
                tasks.set((i - 1 +tasks.size())% tasks.size(), temp);
                return;
            }
        }
    }

}
