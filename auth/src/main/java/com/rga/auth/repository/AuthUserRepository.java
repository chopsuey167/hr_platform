package com.rga.auth.repository;

import com.rga.auth.model.AuthUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {

  Optional<AuthUser> findByUsername(String username);
}
