package com.bibro.service;

import com.bibro.domain.Language;
import com.bibro.domain.Task;
import com.bibro.domain.User;
import com.bibro.domain.UserCode;
import com.bibro.repository.TaskRepository;
import com.bibro.repository.UserRepository;
import com.bibro.request.UserCodeRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    private UserRepository userRepository;
    private TaskRepository taskRepository;
    private BCryptPasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, TaskRepository taskRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User saveWork(UserCode userCode) {
        User user = userCode.getUser();
        return userRepository.save(user.saveWork(userCode));
    }

    public UserCode createUserCodeFromRequest(String username, UserCodeRequest userCodeRequest) {
        User user = userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new);
        Task task = taskRepository.findById(userCodeRequest.getTaskId()).orElseThrow(NoSuchElementException::new);
        Language language = userCodeRequest.getLanguage();
        String code = userCodeRequest.getCode();
        return new UserCode(user, code, task, language);
    }
}
