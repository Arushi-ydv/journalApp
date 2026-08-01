package com.arushi.journalapp.controller;

import com.arushi.journalapp.entity.User;
import com.arushi.journalapp.repository.UserRepository;
import com.arushi.journalapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        String userName = getLoggedInUserName();
        User userInDb = userService.findByUserName(userName);

        if(user.getUserName() != null && !user.getUserName().isBlank()) {
            userInDb.setUserName(user.getUserName());
        }
        if(user.getPassword() != null && !user.getPassword().isBlank()) {
            userInDb.setPassword(user.getPassword());
        }

        userService.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteUserById(){
        userRepository.deleteByUserName(getLoggedInUserName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    public ResponseEntity<?> greeting() {
        return ResponseEntity.ok("Welcome " + getLoggedInUserName());
    }

    private String getLoggedInUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
