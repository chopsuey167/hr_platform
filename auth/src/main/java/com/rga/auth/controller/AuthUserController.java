package com.rga.auth.controller;

import com.rga.auth.dto.AuthUserDto;
import com.rga.auth.dto.TokenDto;
import com.rga.auth.model.AuthUser;
import com.rga.auth.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthUserController {

    private AuthUserService authUserService;

    @Autowired
    public AuthUserController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthUserDto authUserDto){
        TokenDto tokenDto = authUserService.login(authUserDto);
        if(tokenDto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token){
        TokenDto tokenDto = authUserService.validate(token);
        if(tokenDto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tokenDto);
    }

}
