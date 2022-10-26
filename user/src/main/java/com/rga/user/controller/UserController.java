package com.rga.user.controller;

import com.rga.user.dto.UserDto;
import com.rga.user.model.User;
import com.rga.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createNewUser(@RequestBody UserDto userDto) {
        User user = userService.create(userDto);

        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PatchMapping(path = "{userId}")
    public ResponseEntity<User> updateUser(
            @PathVariable("userId") Integer userId,
            @RequestBody UserDto userDto){

        User user = userService.update(userId,userDto);

        if (user == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }


}
