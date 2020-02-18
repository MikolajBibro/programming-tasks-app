package com.bibro.service;

import com.bibro.domain.Language;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.core.command.BuildImageResultCallback;
import com.github.dockerjava.core.command.LogContainerResultCallback;
import com.github.dockerjava.core.command.WaitContainerResultCallback;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class DockerService {

    private DockerClient dockerClient;

    public DockerService(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    public String createImage(String filename, Language language) {
        return dockerClient.buildImageCmd()
                .withDockerfile(new File(language.getDockerfile()))
                .withBuildArg( "filename", filename)
                .withNoCache(true)
                .exec(new BuildImageResultCallback())
                .awaitImageId();
    }

    public CreateContainerResponse createContainer(String image, String input) {
        return dockerClient.createContainerCmd(image).withEnv("INPUT=" + input).exec();
    }

    public void startContainer(String containerId) {
        dockerClient.startContainerCmd(containerId).exec();
    }

    public void waitForResult(String containerId) throws InterruptedException {
            WaitContainerResultCallback resultCallback = new WaitContainerResultCallback();
            dockerClient.waitContainerCmd(containerId).exec(resultCallback);
            resultCallback.awaitCompletion();
    }

    public String getDockerStdOutput(String containerId) {
        return Try.of(() -> tryGetDockerStdOutput(containerId)).get();
    }

    public String tryGetDockerStdOutput(String containerId) throws InterruptedException {
        List<String> logs = new ArrayList<>();

        dockerClient.logContainerCmd(containerId).withStdOut(true).withStdErr(true).exec(new LogContainerResultCallback() {
            @Override
            public void onNext(Frame item) {
                logs.add(item.toString().replaceAll("STDOUT: ", ""));
            }
        }).awaitCompletion();

        return logs.get(0);
    }
}
