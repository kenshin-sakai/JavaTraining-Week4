package com.example.taskapp.controller;

import com.example.taskapp.controller.form.TaskForm;
import com.example.taskapp.entity.Task;
import com.example.taskapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskViewController {

    private final TaskService taskService;

    public TaskViewController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String index(Model model) {
        List<Task> tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);
        return "tasks/index";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("taskForm", new TaskForm());
        model.addAttribute("formAction", "/tasks");
        model.addAttribute("pageTitle", "タスク新規作成");
        return "tasks/form";
    }

    @PostMapping
    public String create(@Validated @ModelAttribute("taskForm") TaskForm form,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes ra) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("formAction", "/tasks");
            model.addAttribute("pageTitle", "タスク新規作成");
            return "tasks/form";
        }

        taskService.create(form.getTitle());
        ra.addFlashAttribute("success", "登録しました");
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Task task = taskService.findById(id);
        model.addAttribute("taskForm", TaskForm.from(task));
        model.addAttribute("formAction", "/tasks/" + id);
        model.addAttribute("pageTitle", "タスク編集");
        return "tasks/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Validated @ModelAttribute("taskForm") TaskForm form,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes ra) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("formAction", "/tasks/" + id);
            model.addAttribute("pageTitle", "タスク編集");
            return "tasks/form";
        }

        taskService.update(id, form.getTitle(), form.isCompleted());
        ra.addFlashAttribute("success", "更新しました");
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        taskService.delete(id);
        ra.addFlashAttribute("success", "削除しました");
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/toggle")
    public String toggle(@PathVariable Long id, RedirectAttributes ra) {
        taskService.toggleCompleted(id);
        ra.addFlashAttribute("success", "状態を更新しました");
        return "redirect:/tasks";
    }
}

