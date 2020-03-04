package com.bibro.domain.task;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "task")
@Inheritance(strategy = InheritanceType.JOINED)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private TaskType type;
    private String task;
    private String description;
    private String input;
    private String output;

    public Task(String task, TaskType type, String description, String input, String output) {
        this.task = task;
        this.type = type;
        this.description = description;
        this.input = input;
        this.output = output;
    }

}
