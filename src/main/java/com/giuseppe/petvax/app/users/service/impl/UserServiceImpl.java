package com.giuseppe.petvax.app.users.service.impl;

import com.giuseppe.petvax.app.users.events.UserRegistrationEventPublisher;
import com.giuseppe.petvax.app.users.model.Users;
import com.giuseppe.petvax.app.users.repository.UserRepository;
import com.giuseppe.petvax.app.users.request.UserRequest;
import com.giuseppe.petvax.app.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRegistrationEventPublisher userRegistrationEventPublisher;
    @Override
    public void createUser(UserRequest userRequest) {
        log.info("Creating user {}", userRequest);
        Users users = Users.builder()
                .nombres(userRequest.nombres())
                .apellidos(userRequest.apellidos())
                .email(userRequest.email())
                .build();
        userRepository.save(users);
        userRegistrationEventPublisher.sendUserCreationEvent(users);
        log.info("User created {}", users);
    }
}
