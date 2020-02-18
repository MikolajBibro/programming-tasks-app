package com.bibro.controller;

import com.bibro.domain.User;
import com.bibro.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class UserController {

    private UserService userService;

    @PostMapping("/sign-up")
    public User addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
