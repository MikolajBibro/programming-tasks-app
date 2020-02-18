package com.bibro.request;

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
    private String description;
    @NotNull
    private String input;
    @NotNull
    private String output;
}
