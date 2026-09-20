package com.arushi.journalapp.controller;

import com.arushi.journalapp.dto.UserResponse;
import com.arushi.journalapp.entity.User;
import com.arushi.journalapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {

        List<User> all = userService.getAll();

        List<UserResponse> responses = all.stream()
                .map(user -> {
                    UserResponse response = new UserResponse();
                    response.setUserName(user.getUserName());
                    response.setEmail(user.getEmail());
                    return response;
                })
                .toList();

        return new ResponseEntity<>(responses,HttpStatus.OK);
    }

    @PostMapping("/create-admin-user")
    public ResponseEntity<?> createUser(@RequestBody User user) {

        userService.saveAdmin(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
