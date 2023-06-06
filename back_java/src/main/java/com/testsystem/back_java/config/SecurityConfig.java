package com.testsystem.back_java.config;

import com.testsystem.back_java.security.jwt.JwtConfigurer;
import com.testsystem.back_java.security.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private static final String ADMIN_ENDPOINT = "/api/v1/admin/**";
    private static final String LOGIN_ENDPOINT = "/api/v1/auth/login";
    private final AuthenticationConfiguration authConfiguration;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider, AuthenticationConfiguration authConfiguration) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authConfiguration = authConfiguration;
    }
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authConfiguration.getAuthenticationManager();
    }


   /* @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }*/


    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests((auth)->auth
                        .requestMatchers("/auth/singup").permitAll()
                        .requestMatchers("/auth/login").permitAll()
                        .anyRequest().authenticated())
                .apply(new JwtConfigurer(jwtTokenProvider));
        return http.build();
    }



}