package com.bibro.service;

import com.bibro.domain.task.Challenge;
import com.bibro.domain.task.Task;
import com.bibro.domain.task.TaskOnTime;
import com.bibro.domain.user.User;
import com.bibro.domain.user.UserCode;
import com.bibro.repository.ChallengeRepository;
import com.bibro.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class ChallengeService {

    private ChallengeRepository challengeRepository;
    private UserRepository userRepository;

    public void initializeChallenge(String username, Task task) {
        User user = userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new);
        if (challengeRepository.findByTaskAndUser(task, user).isEmpty()) {
            Challenge challenge = new Challenge(task, user);
            challengeRepository.save(challenge);
        }
    }

    public UserCode finishChallenge(UserCode userCode) {
        TaskOnTime task = (TaskOnTime) userCode.getTask();
        User user = userCode.getUser();
        Challenge challenge = challengeRepository.findByTaskAndUser(task, user).orElseThrow(RuntimeException::new);
        challenge.setEnd(LocalDateTime.now());
        return userCode;
    }
}
