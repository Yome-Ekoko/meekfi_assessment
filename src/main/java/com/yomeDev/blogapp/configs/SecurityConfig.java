package com.yomeDev.blogapp.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    //    public SecurityConfig(UserDetailsService userDetailsService){
//        this.userDetailsService=userDetailsService;
//    }
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize)->
//                        authorize.anyRequest().authenticated())
                                authorize.requestMatchers(HttpMethod.GET,"/api/v1/**").permitAll()
                                        .requestMatchers("/api/v1/auth/**").permitAll()
                                        .anyRequest().authenticated()
                );
        return http.build();
    }
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails admin= User.builder()
//                .username("Mega-Stallion")
//                .password(passwordEncoder().encode("12345"))
//                .roles("ADMIN")
//                .build();
////
////        UserDetails yome= User.builder()
////                .username("yome")
////                .password(passwordEncoder().encode("1234"))
////                .roles("USER")
////                .build();
//        return new InMemoryUserDetailsManager(admin);
//    }
}


