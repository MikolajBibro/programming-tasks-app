package com.bibro.service;

import com.bibro.domain.user.UserCode;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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
        String path = userCode.getLanguage().getDirectoryPath();
        String filename = userCode.getUser().getUsername() + userCode.getTask().getTask() + userCode.getLanguage().getExtension();
        Path filePath = Paths.get(path,filename);
        Files.writeString(filePath, userCode.getCode(), StandardOpenOption.CREATE_NEW);
        return filename;
    }
}
