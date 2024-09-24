package com.iamcaye.user_service;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iamcaye.user_service.dto.UserCreateDto;
import com.iamcaye.user_service.models.User;

@RestController
@RequestMapping("/api/user")
public class UserController {
    final UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public User createUser(@RequestBody UserCreateDto user) {
        System.out.println(user);
        User newUser = this.userService.createUser(user);
        this.userService.saveLocalCredentials(newUser, user.password());

        return newUser;
    }

    @GetMapping("")
    public List<User> getUsers () {
        return this.userService.getUsers();
    }
}
