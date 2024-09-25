package com.iamcaye.user_service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.iamcaye.user_service.dto.UserCreateDto;
import com.iamcaye.user_service.models.LoginProvider;
import com.iamcaye.user_service.models.User;
import com.iamcaye.user_service.models.UserCredentials;
import com.iamcaye.user_service.repositories.UserCredentialsRepository;
import com.iamcaye.user_service.repositories.UserRepository;

@Service
public class UserService {
    final UserRepository userRepository;
    final UserCredentialsRepository userCredentialsRepository;
    final static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public UserService (
        UserRepository userRepository,
        UserCredentialsRepository userCredentialsRepository
    ) {
        this.userRepository = userRepository;
        this.userCredentialsRepository = userCredentialsRepository;
    }

    public User createUser (UserCreateDto user) {
        User newUser = new User(user);
        return this.userRepository.save(newUser);
    }

    public Boolean saveLocalCredentials (User user, String password) {
        UserCredentials credentials = new UserCredentials(null, user.getId(), LoginProvider.LOCAL.ordinal(), encoder.encode(password));
        
        return this.userCredentialsRepository.save(credentials) != null;
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public User getUserByIdentificator(String identificator) {
        return this.userRepository.getUserByIdentificator(identificator);
    }

    public Boolean checkCredentials(String password, UserCredentials credentials) {
        return encoder.matches(password, credentials.hash());
    }

    public List<UserCredentials> getUserCredentialsByUserId (Integer userId) {
        return this.userCredentialsRepository.findAllByUserId(userId);
    }
}
