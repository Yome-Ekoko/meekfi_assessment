package com.yomeDev.blogapp.services.serviesimpls;

import com.yomeDev.blogapp.configs.security.JwtUtils;
import com.yomeDev.blogapp.exceptions.BlogApiException;
import com.yomeDev.blogapp.models.Role;
import com.yomeDev.blogapp.models.User;
import com.yomeDev.blogapp.payloads.ApiResponseDto;
import com.yomeDev.blogapp.payloads.LoginDto;
import com.yomeDev.blogapp.payloads.RegisterDto;
import com.yomeDev.blogapp.repositories.RoleRepo;
import com.yomeDev.blogapp.repositories.UserRepo;
import com.yomeDev.blogapp.services.AuthService;
import com.yomeDev.blogapp.utils.ResponseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private RoleRepo roleRepository;

    @Autowired
    private ResponseManager responseManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private JwtUtils jwtTokenProvider;
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepo userRepository,
                           RoleRepo roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtils jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }

    @Override
    public ApiResponseDto<String> register(RegisterDto registerDto) {
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Username  already exists!.");

        }

        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Email already exists!.");
        }
        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("USER").get();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
        return responseManager.success(user);
    }
}

