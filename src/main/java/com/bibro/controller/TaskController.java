package com.bibro.controller;


import com.bibro.domain.task.Language;
import com.bibro.domain.task.Task;
import com.bibro.domain.task.TaskType;
import com.bibro.domain.user.UserCode;
import com.bibro.repository.TaskRepository;
import com.bibro.request.ChallengeOnTimeRequest;
import com.bibro.request.TaskRequest;
import com.bibro.request.UserCodeRequest;
import com.bibro.service.ChallengeService;
import com.bibro.service.SubmissionService;
import com.bibro.service.TaskService;
import com.bibro.service.UserCodeService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@RestController
public class TaskController {

    private UserCodeService userCodeService;
    private TaskService taskService;
    private TaskRepository taskRepository;
    private SubmissionService submissionService;
    private ChallengeService challengeService;

    @GetMapping(value = "/standard-tasks")
    public List<Task> getAllStandardTasks() {
        return taskRepository.findByType(TaskType.Task);
    }

    @GetMapping(value = "/tasks-on-time")
    public List<Task> getAllTasksOnTime() {
        return taskRepository.findByType(TaskType.TaskOnTime);
    }

    @PostMapping(value = "/tasks")
    public void addTask(@RequestBody TaskRequest taskRequest) {
        Task task = taskService.createTaskFromRequest(taskRequest);
        taskRepository.save(task);
    }

    @GetMapping(value = "/standard-tasks/{name}")
    public Task getTask(@PathVariable("name") String name) {
        return taskRepository.findByTaskAndType(name, TaskType.Task).orElseThrow(NoSuchElementException::new);
    }

    @PostMapping(value = "/challenge-on-time")
    public Task startChallengeOnTime(Principal principal, @RequestBody ChallengeOnTimeRequest challengeOnTimeRequest) {
        Task task = taskRepository.findByTaskAndType(challengeOnTimeRequest.getTaskTitle(), TaskType.TaskOnTime).orElseThrow(NoSuchElementException::new);
        challengeService.initializeChallenge(principal.getName(), task);
        return task;
    }

    @MessageMapping(value = "/submit-standard-task-answer")
    @SendToUser("/queue/standard-task")
    public boolean submitStandardTaskAnswer(Principal principal, UserCodeRequest userCodeRequest) {
        UserCode userCode = userCodeService.createUserCodeFromRequest(principal.getName(), userCodeRequest);
        return submissionService.submit(userCode);
    }

    @MessageMapping(value = "/submit-task-on-time-answer")
    @SendToUser("/queue/task-on-time")
    public boolean submitTaskOnTimeAnswer(Principal principal, UserCodeRequest userCodeRequest) {
        UserCode userCode = userCodeService.createUserCodeFromRequest(principal.getName(), userCodeRequest);
        challengeService.finishChallenge(userCode);
        return submissionService.submit(userCode);
    }



    @GetMapping(value = "/languages")
    public List<String> getLanguages() {
        return Language.getLanguages();
    }
}

