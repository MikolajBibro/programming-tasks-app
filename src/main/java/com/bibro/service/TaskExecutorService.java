package com.bibro.service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import org.springframework.stereotype.Service;

@Service
public class TaskExecutorService {

    private DockerService dockerService;

    public TaskExecutorService(DockerService dockerService) {
        this.dockerService = dockerService;
    }

    public void executeUserCode(String imageName) throws InterruptedException {
        CreateContainerResponse container = dockerService.createContainer(imageName);
        dockerService.startContainer(container.getId());
        dockerService.waitForResult(container.getId());
        dockerService.getDockerStdOutput(container.getId());
    }
}
