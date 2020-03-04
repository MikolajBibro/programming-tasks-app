package com.bibro.request;

import com.bibro.domain.task.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCodeRequest {

    @NotNull
    private String code;
    @NotNull
    private Language language;
    @NotNull
    private String task;
    @NotNull
    private String startTime;
    @NotNull
    private String endTime;


}
