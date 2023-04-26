package com.yomeDev.blogapp.configs.security;

import com.yomeDev.blogapp.models.User;
import com.yomeDev.blogapp.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
@Service
public class CostomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user= userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail)
                .orElseThrow(()-> new UsernameNotFoundException("User not Found with email or username" + usernameOrEmail));

        Set<GrantedAuthority> authorities=user
                .getRoles()
                .stream()
                .map((role)-> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails
                .User(user.getEmail(),
                user.getPassword(),
                authorities);
    }
}
