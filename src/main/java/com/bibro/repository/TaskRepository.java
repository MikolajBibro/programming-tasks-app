package com.bibro.repository;

import com.bibro.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Integer> {
    List<Task> findAll();
    Optional<Task> findByTask(String name);
}
