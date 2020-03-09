package com.bibro.controller;

import com.bibro.domain.user.User;
import com.bibro.request.UserRequest;
import com.bibro.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
public class UserController {

    private UserService userService;

    @PostMapping("/sign-up")
    public void addUser(@Valid @RequestBody UserRequest userRequest) {
        userService.create(userRequest);
    }

    @GetMapping("/confirm-registration")
    public void confirm(@RequestParam("token") String token) {
        userService.enableAccount(token);
    }
}
