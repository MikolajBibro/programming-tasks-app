package com.bibro.domain.user;

import com.bibro.domain.task.Language;
import com.bibro.domain.task.Task;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user_code")
public class UserCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    @ManyToOne
    private Task task;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.STRING)
    private Language language;

    public UserCode(User user, String code, Task task, Language language) {
        this.user = user;
        this.code = code;
        this.task = task;
        this.language = language;
    }

    public String getInputForTask() {
        return task.getInput();
    }

    public String getOutputForTask() { return task.getOutput(); }

}
