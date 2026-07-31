package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.exception.UserAlreadyExistsException;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveNewUser(User user) {

        User existingUser = userRepository.findByUserName(user.getUserName());

        if(existingUser != null){
            throw new UserAlreadyExistsException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        user.setSentimentAnalysis(false);
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

    public Optional<User> findById(ObjectId id) {

        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id) {

        userRepository.deleteById(id);
    }

    public User findByUserName(String userName) {

        return userRepository.findByUserName(userName);
    }

    public void deleteByUserName(String userName) {
        userRepository.deleteByUserName(userName);
    }
}

//controller ---> service ---> repository
