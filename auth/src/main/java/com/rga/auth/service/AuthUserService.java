package com.rga.auth.service;

import com.rga.auth.dto.AuthUserDto;
import com.rga.auth.dto.TokenDto;
import com.rga.auth.model.AuthUser;
import com.rga.auth.repository.AuthUserRepository;
import com.rga.auth.security.JwtProvider;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthUserService {

  private final AuthUserRepository authUserRepository;

  private final PasswordEncoder passwordEncoder;

  private final JwtProvider jwtProvider;

  @Autowired
  public AuthUserService(AuthUserRepository authUserRepository,
      PasswordEncoder passwordEncoder,
      JwtProvider jwtProvider) {
    this.authUserRepository = authUserRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtProvider = jwtProvider;
  }

  public TokenDto login(AuthUserDto authUserDto) {
    Optional<AuthUser> user = authUserRepository.findByUsername(authUserDto.getUsername());

    if (!user.isPresent()) {
      return null;
    }

    if (passwordEncoder.matches(authUserDto.getPassword(), user.get().getPassword())) {
      return new TokenDto(jwtProvider.createToken(user.get()));
    }
    return null;
  }

  public TokenDto validate(String token) {

    if (!jwtProvider.validate(token)) {
      return null;
    }
    String username = jwtProvider.getUserNameFromToken(token);
    if (!authUserRepository.findByUsername(username).isPresent()) {
      return null;
    }
    return new TokenDto(token);
  }


}
