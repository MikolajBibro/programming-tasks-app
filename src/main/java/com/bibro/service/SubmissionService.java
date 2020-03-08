package com.bibro.service;

import com.bibro.domain.task.Challenge;
import com.bibro.domain.task.TaskOnTime;
import com.bibro.domain.task.TaskType;
import com.bibro.domain.user.User;
import com.bibro.domain.user.UserCode;
import com.bibro.repository.ChallengeRepository;
import com.bibro.repository.UserCodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class SubmissionService {

    private TaskExecutorService taskExecutorService;
    private UserCodeRepository userCodeRepository;
    private ChallengeRepository challengeRepository;

    public boolean submit(UserCode userCode) {
        if (isTaskSolvedProperly(userCode)) {
            userCodeRepository.save(userCode);
            return true;
        }
        return false;
    }

    private boolean isTaskSolvedProperly(UserCode userCode) {
        TaskType type = userCode.getTask().getType();

        switch (type) {
            case TaskOnTime:
                return isAnswerCorrect(userCode) && isDoneInRequiredTime(userCode);
            case Task:
                return isAnswerCorrect(userCode);
            default:
                return false;
        }
    }

    private boolean isAnswerCorrect(UserCode userCode) {
        String output = userCode.getOutputForTask();
        return taskExecutorService.executeProgram(userCode).equals(output);
    }

    private boolean isDoneInRequiredTime(UserCode userCode) {
        TaskOnTime task = (TaskOnTime) userCode.getTask();
        User user = userCode.getUser();
        Challenge challenge = challengeRepository.findByTaskAndUser(task, user).orElseThrow(NoSuchElementException::new);
        LocalDateTime start = challenge.getStart();
        LocalDateTime end = challenge.getEnd();
        long minutes = ChronoUnit.MINUTES.between(start, end);
        return minutes < task.getTimeForTask();
    }
}
