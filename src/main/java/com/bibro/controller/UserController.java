package com.bibro.controller;

import com.bibro.domain.User;
import com.bibro.request.UserRequest;
import com.bibro.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
public class UserController {

    private UserService userService;

    @PostMapping("/sign-up")
    public void addUser(@Valid @RequestBody UserRequest userRequest) {
        User user = userService.createUserFromUserRequest(userRequest);
        userService.saveUser(user);
    }
}
