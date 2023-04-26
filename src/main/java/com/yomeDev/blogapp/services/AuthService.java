package com.yomeDev.blogapp.services;

import com.yomeDev.blogapp.payloads.LoginDto;
import com.yomeDev.blogapp.payloads.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
