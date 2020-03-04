package com.bibro.repository;

import com.bibro.domain.task.Task;
import com.bibro.domain.user.User;
import com.bibro.domain.user.UserCode;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserCodeRepository extends CrudRepository<UserCode, Integer> {

    Optional<UserCode> findByUserAndTask(User user, Task task);
}
