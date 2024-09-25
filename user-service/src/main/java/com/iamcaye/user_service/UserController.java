package com.iamcaye.user_service;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iamcaye.user_service.dto.UserCreateDto;
import com.iamcaye.user_service.dto.UserLoginDto;
import com.iamcaye.user_service.models.LoginProvider;
import com.iamcaye.user_service.models.User;
import com.iamcaye.user_service.models.UserCredentials;
import com.iamcaye.user_service.utils.JwtUtil;

@RestController
@RequestMapping("/api/user")
public class UserController {
    final UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public User createUser(@RequestBody UserCreateDto user) {
        User newUser = this.userService.createUser(user);
        this.userService.saveLocalCredentials(newUser, user.password());

        return newUser;
    }

    @PostMapping("login")
    public String login (@RequestBody UserLoginDto userLogin) throws Exception {
        User user = this.userService.getUserByIdentificator(userLogin.username());
        List<UserCredentials> credentialsList = this.userService.getUserCredentialsByUserId(user.getId());
        UserCredentials credentials = credentialsList.stream()
            .filter(uc -> uc.provider_id().equals(LoginProvider.LOCAL.ordinal()))
            .findFirst()
            .orElseThrow(() -> new Exception("No credentials"));

        if(!this.userService.checkCredentials(userLogin.password(), credentials)) {
            throw new Exception("bad");
        }

        return JwtUtil.generateToken(user);
    }

    @GetMapping("")
    public List<User> getUsers () {
        return this.userService.getUsers();
    }
}
