package com.bibro.service;

import com.bibro.domain.Language;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TaskExecutorService {

    private DockerService dockerService;

    public String executeProgram(String filename, String input, Language language) {
        return Try.of(() -> (tryExecuteProgram(filename, input, language))).get();
    }

    public String tryExecuteProgram(String filename, String input, Language language) throws InterruptedException {
        String image = dockerService.createImage(filename, language);
        String containerId = dockerService.createContainer(image, input).getId();
        dockerService.startContainer(containerId);
        dockerService.waitForResult(containerId);
        return dockerService.getDockerStdOutput(containerId);
    }
}
