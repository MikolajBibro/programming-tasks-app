package com.bibro.service;

import com.bibro.domain.Language;
import com.bibro.domain.UserCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SubmissionService {

    private TaskExecutorService taskExecutorService;
    private UserService userService;

    public boolean submit(UserCode userCode) {
        if (isAnswerCorrect(userCode)) {
            userService.saveWork(userCode);
            return true;
        }
        return false;
    }

    public boolean isAnswerCorrect(UserCode userCode) {
        String filename = userCode.saveToFile();
        String input = userCode.getInputForTask();
        String output = userCode.getOutputForTask();
        Language language = userCode.getLanguage();
        return taskExecutorService.executeProgram(filename, input, language).equals(output);
    }
}
