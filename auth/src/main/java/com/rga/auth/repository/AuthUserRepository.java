package com.rga.auth.repository;

import com.rga.auth.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser,Integer> {

    Optional<AuthUser> findByUsername(String username);
}
