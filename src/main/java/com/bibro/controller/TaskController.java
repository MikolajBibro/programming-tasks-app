package com.bibro.controller;


import com.bibro.domain.*;
import com.bibro.repository.QuestionRepository;
import com.bibro.repository.TaskRepository;
import com.bibro.request.TaskRequest;
import com.bibro.request.UserCodeRequest;
import com.bibro.service.SubmissionService;
import com.bibro.service.TaskService;
import com.bibro.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@RestController
public class TaskController {

    private UserService userService;
    private TaskService taskService;
    private TaskRepository taskRepository;
    private QuestionRepository questionRepository;

    private SubmissionService submissionService;

    @GetMapping(value = "/tasks")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @PostMapping(value = "/tasks")
    public void addTask(@AuthenticationPrincipal String username, @RequestBody TaskRequest taskRequest) {
        Task task = taskService.createTaskFromRequest(username, taskRequest);
        taskRepository.save(task);
    }

    @GetMapping(value = "/tasks/{name}")
    public Task getTask(@PathVariable("name") String name) {
        return taskRepository.findByTask(name).orElseThrow(NoSuchElementException::new);
    }

    @PostMapping(value = "/submit")
    public boolean submitAnswer(@AuthenticationPrincipal String username, @RequestBody UserCodeRequest userCodeRequest) {
        UserCode userCode = userService.createUserCodeFromRequest(username, userCodeRequest);
        return submissionService.submit(userCode);
    }

    @ResponseBody
    @GetMapping(value = "/test")
    public String getAllTasks(@AuthenticationPrincipal String username) {
        return username;
    }
}

