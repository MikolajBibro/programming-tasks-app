package com.bibro.request;

import com.bibro.domain.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCodeRequest {
    private String code;
    private Language language;
    private int taskId;
}
