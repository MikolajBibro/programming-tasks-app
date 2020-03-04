package com.bibro.service;

import com.bibro.domain.task.Language;
import com.bibro.domain.user.User;
import com.bibro.domain.user.UserCode;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@AllArgsConstructor
@Service
public class TaskExecutorService {

    private DockerService dockerService;

    public String executeProgram(UserCode userCode) {
        return Try.of(() -> (tryExecuteProgram(userCode))).get();
    }

    public String tryExecuteProgram(UserCode userCode) throws InterruptedException {
        String filename = saveToFile(userCode);
        String image = dockerService.createImage(filename, userCode.getLanguage());
        String containerId = dockerService.createContainer(image, userCode.getInputForTask()).getId();
        dockerService.startContainer(containerId);
        dockerService.waitForResult(containerId);
        return dockerService.getDockerStdOutput(containerId);
    }

    public String saveToFile(UserCode userCode) {
        return Try.of(() -> this.trySaveToFile(userCode)).get();
    }

    public String trySaveToFile(UserCode userCode) throws IOException {
        String path = "C:\\Users\\Mikolaj\\Desktop\\new\\";
        String filename = userCode.getUser().getUsername() + userCode.getTask() + userCode.getLanguage().getExtension();
        BufferedWriter writer = new BufferedWriter(new FileWriter(path + filename, true));
        writer.append(userCode.getCode());
        writer.close();
        return filename;
    }
}
