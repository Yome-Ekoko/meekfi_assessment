package com.yomeDev.blogapp.services;

import com.yomeDev.blogapp.payloads.ApiResponseDto;
import com.yomeDev.blogapp.payloads.LoginDto;
import com.yomeDev.blogapp.payloads.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    ApiResponseDto<String> register(RegisterDto registerDto);
}
