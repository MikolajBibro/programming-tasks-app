package com.bibro.service;

import com.bibro.domain.user.RegistrationToken;
import com.bibro.domain.user.User;
import com.bibro.repository.RegistrationTokenRepository;
import com.bibro.repository.UserRepository;
import com.bibro.request.UserRequest;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;
    private RegistrationTokenRepository registrationTokenRepository;
    private EmailService emailService;
    private BCryptPasswordEncoder passwordEncoder;


    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User createUserFromUserRequest(UserRequest userRequest) {
        return new User(userRequest.getUsername(), userRequest.getEmail(), userRequest.getPassword());
    }

    public void enableAccount(String token) {
        RegistrationToken registrationToken = registrationTokenRepository.findByToken(token);
        User user = registrationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);
    }

    public RegistrationToken createAndSendRegistrationToken(User user) {
        String token = RandomStringUtils.random(20, true, true);
        RegistrationToken registrationToken = new RegistrationToken(token, user);
        registrationTokenRepository.save(registrationToken);
        emailService.sendRegistrationToken(registrationToken);
        return registrationToken;
    }
}
