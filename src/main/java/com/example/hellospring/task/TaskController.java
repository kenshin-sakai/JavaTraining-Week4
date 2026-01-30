package com.example.hellospring.task;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getTasks() {
        return taskService.findAll();
    }

    @PostMapping
    public Task createTask(@RequestBody Task request) {
        String title = request.getTitle();
        if (title == null || title.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "title must not be blank");
        }
        return taskService.create(title);
    }
}
