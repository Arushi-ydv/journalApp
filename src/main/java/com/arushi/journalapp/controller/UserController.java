package com.arushi.journalapp.controller;

import com.arushi.journalapp.dto.UserUpdateRequest;
import com.arushi.journalapp.entity.User;
import com.arushi.journalapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService,
                          PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateRequest request) {

        String userName = getLoggedInUserName();
        User userInDb = userService.findByUserName(userName);

        if (request.getUserName() != null && !request.getUserName().isBlank()) {
            userInDb.setUserName(request.getUserName());
        }

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            userInDb.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        userService.saveUser(userInDb);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteUserById() {

        userService.deleteByUserName(getLoggedInUserName());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    public ResponseEntity<?> greeting() {
        return ResponseEntity.ok("Welcome " + getLoggedInUserName());
    }

    private String getLoggedInUserName() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }
}
