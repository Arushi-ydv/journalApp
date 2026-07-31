package com.arushi.journalapp.controller;


import com.arushi.journalapp.dto.response.WeatherResponse;
import com.arushi.journalapp.entity.User;
import com.arushi.journalapp.repository.UserRepository;
import com.arushi.journalapp.service.UserService;
import com.arushi.journalapp.service.WeatherService;
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

    @Autowired
    private WeatherService weatherService;

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
    public ResponseEntity<?> greeting(){
        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");
        String greeting = "";
        if(weatherResponse != null) {
            greeting = ", weather feels like " + weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi " + getLoggedInUserName() + greeting, HttpStatus.OK);
    }

    private String getLoggedInUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
