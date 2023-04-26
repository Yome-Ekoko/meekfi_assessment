package com.yomeDev.blogapp.configs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    private final String[] WHITE_LISTED_URLS = { "/", "index", "/css/*", "/js/*", "/api/v1/auth/**",
              "/configuration/**", "/swagger*/**","/swagger-ui/**","/webjars/**", "/swagger-ui.html",
            "/api/v1/auth/signup", "/api/v1/auth/login", "/api/v1/auth/signin", "/api/v1/auth/register",
            "/api/v1/category/create-category","/api/v1/post/create-post/{id}","/api/v1/post/{id}/delete",
            "/api/v1/post/delete-post/{id}", "/api/v1/post/update",
    }
            ;
    private UserDetailsService userDetailsService;
    private JwtAuthEntryPoint authenticationEntryPoint;

    private JwtAuthFilter authenticationFilter;

    public SecurityConfig(UserDetailsService userDetailsService,
                          JwtAuthEntryPoint authenticationEntryPoint,
                          JwtAuthFilter authenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers(HttpMethod.GET,WHITE_LISTED_URLS).permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
                                .requestMatchers("/api/v1/auth/**").permitAll()

                                .anyRequest().authenticated()
                ).exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint)
                ).sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}


