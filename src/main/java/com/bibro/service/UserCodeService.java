package com.bibro.service;

import com.bibro.domain.task.Challenge;
import com.bibro.domain.task.Language;
import com.bibro.domain.task.Task;
import com.bibro.domain.task.TaskOnTime;
import com.bibro.domain.user.User;
import com.bibro.domain.user.UserCode;
import com.bibro.repository.ChallengeRepository;
import com.bibro.repository.TaskRepository;
import com.bibro.repository.UserRepository;
import com.bibro.request.UserCodeRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class UserCodeService {

    private UserRepository userRepository;
    private ChallengeRepository challengeRepository;
    private TaskRepository taskRepository;

    public UserCode createUserCodeFromRequest(String username, UserCodeRequest userCodeRequest) {
        User user = userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new);
        Task task = taskRepository.findByTask(userCodeRequest.getTask()).orElseThrow(NoSuchElementException::new);
        Language language = userCodeRequest.getLanguage();
        String code = userCodeRequest.getCode();
        return new UserCode(user, code, task, language);
    }

}
