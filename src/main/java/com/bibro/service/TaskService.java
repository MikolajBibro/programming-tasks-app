package com.bibro.service;

import com.bibro.domain.task.Task;
import com.bibro.domain.task.TaskOnTime;
import com.bibro.domain.task.TaskType;
import com.bibro.request.TaskRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TaskService {

    public Task createTaskFromRequest(TaskRequest taskRequest) {
        TaskType type = taskRequest.getType();
        String description = taskRequest.getDescription();
        String input = taskRequest.getInput();
        String output = taskRequest.getOutput();
        String task = taskRequest.getTask();
        int timeForTask = taskRequest.getTimeForTask();

        switch (type) {
            case Task:
                return new Task(task, type, description, input, output);
            case TaskOnTime:
                return new TaskOnTime(task, type, description, input, output, timeForTask);
            default:
                return null;
        }
    }
}
