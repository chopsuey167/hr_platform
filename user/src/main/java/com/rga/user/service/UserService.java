package com.rga.user.service;

import com.rga.user.dto.UserDto;
import com.rga.user.model.User;
import com.rga.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(UserDto userDto) {

        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            return null;
        }

        String password = passwordEncoder.encode(userDto.getPassword());

        User newUser = User.builder()
                .username(userDto.getUsername())
                .password(password)
                .active(true)
                .build();

        return userRepository.save(newUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User update(Integer userId, UserDto userDto) {
        Optional<User> user = userRepository.findById(userId);

        if (!user.isPresent()) {
            return null;
        }

        if (userDto.getPassword() != null) {
            String password = passwordEncoder.encode(userDto.getPassword());
            user.get().setPassword(password);
        }

        user.get().setActive(userDto.getActive());

        return userRepository.save(user.get());
    }


}
