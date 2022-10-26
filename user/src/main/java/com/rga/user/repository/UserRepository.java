package com.rga.user.repository;

import com.rga.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);
}
