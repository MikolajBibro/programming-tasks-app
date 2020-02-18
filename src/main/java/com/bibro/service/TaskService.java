package com.bibro.service;

import com.bibro.domain.Task;
import com.bibro.domain.User;
import com.bibro.repository.UserRepository;
import com.bibro.request.TaskRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class TaskService {

    private UserRepository userRepository;

    public Task createTaskFromRequest(String username, TaskRequest taskRequest) {
        User user = userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new);
        return new Task(user, taskRequest.getDescription(), taskRequest.getInput(), taskRequest.getOutput(), taskRequest.getTask());
    }
}
