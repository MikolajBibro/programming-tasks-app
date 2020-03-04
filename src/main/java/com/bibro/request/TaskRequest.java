package com.bibro.request;

import com.bibro.domain.task.TaskType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskRequest {

    @NotNull
    private String username;
    @NotNull
    private String task;
    @NotNull
    private TaskType type;
    @NotNull
    private String description;
    @NotNull
    private String input;
    @NotNull
    private String output;
    @NotNull
    private int timeForTask;
}
