package com.example.taskapp.service;

import com.example.taskapp.entity.Task;
import com.example.taskapp.exception.TaskNotFoundException;
import com.example.taskapp.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public List<Task> findAll() {
        return repo.findAll();
    }

    public Task findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Transactional
    public Task create(String title) {
        return repo.save(new Task(title));
    }

    @Transactional
    public Task update(Long id, String title, boolean completed) {
        Task task = findById(id);
        task.setTitle(title);
        task.setCompleted(completed);
        return task;
    }

    @Transactional
    public void delete(Long id) {
        Task task = findById(id);
        repo.delete(task);
    }
    @Transactional
    public Task toggleCompleted(Long id) {
        Task task = findById(id);
        task.setCompleted(!task.isCompleted());
        return task;
    }

}

