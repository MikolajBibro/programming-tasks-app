package com.bibro.repository;

import com.bibro.domain.task.Challenge;
import com.bibro.domain.task.Task;
import com.bibro.domain.user.User;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ChallengeRepository extends CrudRepository<Challenge, Integer> {
    Optional<Challenge> findByTaskAndUser(Task task, User user);
}
