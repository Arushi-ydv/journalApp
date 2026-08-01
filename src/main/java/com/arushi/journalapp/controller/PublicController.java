package com.arushi.journalapp.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import com.arushi.journalapp.dto.LoginRequest;
import com.arushi.journalapp.dto.LoginResponse;
import com.arushi.journalapp.dto.SignupRequest;
import com.arushi.journalapp.dto.SignupResponse;
import com.arushi.journalapp.entity.User;
import com.arushi.journalapp.service.UserDetailsServiceImpl;
import com.arushi.journalapp.service.UserService;
import com.arushi.journalapp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@Valid @RequestBody SignupRequest request) {

        User user = new User();

        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        userService.saveNewUser(user);

        SignupResponse response = new SignupResponse();
        response.setMessage("User Registered Successfully");
        response.setUserName(user.getUserName());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
            String jwt = jwtUtil.generateToken(userDetails);

            LoginResponse response = new LoginResponse();

            response.setMessage("Login Successful");
            response.setToken(jwt);
            response.setType("Bearer");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(Exception e){
            log.error("Exception occured while createAuthenticationToken ", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
