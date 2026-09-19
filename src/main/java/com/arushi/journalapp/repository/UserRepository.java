package com.arushi.journalapp.repository;

import com.arushi.journalapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);

    void deleteByUserName(String username);

}
