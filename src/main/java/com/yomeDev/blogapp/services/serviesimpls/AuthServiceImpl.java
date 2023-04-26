package com.yomeDev.blogapp.services.serviesimpls;

import com.yomeDev.blogapp.models.Role;
import com.yomeDev.blogapp.models.User;
import com.yomeDev.blogapp.payloads.LoginDto;
import com.yomeDev.blogapp.payloads.RegisterDto;
import com.yomeDev.blogapp.repositories.RoleRepo;
import com.yomeDev.blogapp.repositories.UserRepo;
import com.yomeDev.blogapp.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private PasswordEncoder passwordEncoder;
    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "Successful.";
    }

    @Override
    public String register(RegisterDto registerDto) {
        if(userRepository.existsByUsername(registerDto.getUsername())){
            //throw exception
            return  "user already exist";
        }
        if(userRepository.existsByEmail(registerDto.getEmail())) {
            //throw exception
            return "Email already exist";
        }
        User user= User.builder()
                .username(registerDto.getUsername())
                .email(registerDto.getEmail())
                .name(registerDto.getName())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .build();
        Set<Role> roles =new HashSet<>();
        Role userRole=roleRepository.findByName("USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "User Registered Successfully";
    }
}
