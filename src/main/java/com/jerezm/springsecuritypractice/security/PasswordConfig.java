package com.jerezm.springsecuritypractice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        Integer strength = 10;
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(strength);

        return passwordEncoder;
    }
}
