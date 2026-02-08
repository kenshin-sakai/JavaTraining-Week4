package com.example.taskapp.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ViewExceptionHandler {

    @ExceptionHandler(value = TaskNotFoundException.class, produces = MediaType.TEXT_HTML_VALUE)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handle(TaskNotFoundException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error/404";
    }
}
