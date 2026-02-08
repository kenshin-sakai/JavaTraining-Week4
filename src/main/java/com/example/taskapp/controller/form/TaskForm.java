package com.example.taskapp.controller.form;

import com.example.taskapp.entity.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskForm {

    @NotBlank(message = "タイトルは必須です")
    @Size(max = 50, message = "タイトルは50文字以内で入力してください")
    private String title;

    private boolean completed;

    public TaskForm() {}

    public static TaskForm from(Task task) {
        TaskForm f = new TaskForm();
        f.setTitle(task.getTitle());
        f.setCompleted(task.isCompleted());
        return f;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}
