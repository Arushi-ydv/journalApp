package com.arushi.journalapp.service;

import com.arushi.journalapp.entity.User;
import com.arushi.journalapp.exception.UserAlreadyExistsException;
import com.arushi.journalapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveNewUser(User user) {

        User existingUser = userRepository.findByUserName(user.getUserName());

        if(existingUser != null){
            throw new UserAlreadyExistsException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }

    public void saveAdmin(User user) {

        User existingUser = userRepository.findByUserName(user.getUserName());
        if(existingUser != null) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }

    public void saveUser(User user) {

        userRepository.save(user);
    }

    public List<User> getAll(){

        return userRepository.findAll();
    }

    public User findByUserName(String userName) {

        return userRepository.findByUserName(userName);
    }

    public void deleteByUserName(String userName) {

        userRepository.deleteByUserName(userName);
    }
}

