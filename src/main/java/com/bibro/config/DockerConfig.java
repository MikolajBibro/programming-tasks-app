package com.bibro.config;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.LogContainerResultCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DockerConfig {

    @Bean
    public DockerClient getDockerClient() {
        return DockerClientBuilder.getInstance("tcp://localhost:2375").build();
    }

}
