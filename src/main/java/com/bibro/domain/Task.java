package com.bibro.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String task;
    private String description;
    private String input;
    private String output;

    public Task( String task, String description, String input, String output) {
        this.task = task;
        this.description = description;
        this.input = input;
        this.output = output;
    }

}
