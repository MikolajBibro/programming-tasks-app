package com.bibro.repository;

import com.bibro.domain.task.Task;
import com.bibro.domain.task.TaskType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Integer> {
    List<Task> findAll();
    Optional<Task> findByTask(String name);
    List<Task> findByType(TaskType type);
    Optional<Task> findByTaskAndType(String name, TaskType type);

}
