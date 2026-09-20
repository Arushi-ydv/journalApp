package com.arushi.journalapp.repository;

import com.arushi.journalapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);

    @Transactional
    void deleteByUserName(String username);

}
