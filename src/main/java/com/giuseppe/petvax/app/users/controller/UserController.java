package com.giuseppe.petvax.app.users.controller;

import com.giuseppe.petvax.app.users.request.UserRequest;
import com.giuseppe.petvax.app.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody @Valid UserRequest userRequest){
        userService.createUser(userRequest);
        return ResponseEntity.ok("User created");
    }
}
