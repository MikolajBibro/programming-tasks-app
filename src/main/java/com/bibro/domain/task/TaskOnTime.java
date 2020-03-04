package com.bibro.domain.task;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "task_on_time")
public class TaskOnTime extends Task {

    private int timeForTask;

    public TaskOnTime(String task, TaskType type, String description, String input, String output, int timeForTask) {
        super(task, type, description, input, output);
        this.timeForTask = timeForTask;
    }
}
