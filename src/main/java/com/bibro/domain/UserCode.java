package com.bibro.domain;

import io.vavr.control.Try;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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

    public String saveToFile() {
        return Try.of(this::trySaveToFile).get();
    }

    public String trySaveToFile() throws IOException {
        String path = "C:\\Users\\Mikolaj\\Desktop\\new\\";
        String filename = user.getUsername() + task.getTask() + language.getExtension();
        BufferedWriter writer = new BufferedWriter(new FileWriter(path + filename, true));
        writer.append(code);
        writer.close();
        return filename;
    }

    public String getInputForTask() {
        return task.getInput();
    }

    public String getOutputForTask() { return task.getOutput(); }
}
