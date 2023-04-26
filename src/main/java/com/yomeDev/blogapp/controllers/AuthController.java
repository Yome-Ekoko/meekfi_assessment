package com.yomeDev.blogapp.controllers;

import com.yomeDev.blogapp.payloads.ApiResponseDto;
import com.yomeDev.blogapp.payloads.LoginDto;
import com.yomeDev.blogapp.payloads.RegisterDto;
import com.yomeDev.blogapp.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping(value={"/login","signin"})
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        String response=authService.login(loginDto);

        return ResponseEntity.ok(response);
    }
    @PostMapping(value={"/register","/signup"})
    public  ResponseEntity<ApiResponseDto<String>> registerUser(@RequestBody RegisterDto registerDto){
        ApiResponseDto<String> response= authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
