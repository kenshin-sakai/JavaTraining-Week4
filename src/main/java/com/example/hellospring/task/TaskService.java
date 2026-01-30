package com.example.hellospring.task;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public List<Task> findAll() {
        return tasks;
    }

    public Task create(String title) {
        long id = idGenerator.incrementAndGet();
        Task task = new Task(id, title);
        tasks.add(task);
        return task;
    }
}
