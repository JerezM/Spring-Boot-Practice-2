package com.jerezm.springsecuritypractice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.jerezm.springsecuritypractice.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/", "index").permitAll()
            .antMatchers("/api/**").hasRole(STUDENT.name())
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();

    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        String studentUserName = "facuconte7";
        String studentUserPassword = this.passwordEncoder.encode("password7");

        UserDetails facuConteUser = 
            User.builder()
                .username(studentUserName)
                .password(studentUserPassword)
                .roles(STUDENT.name())   // ROLE_STUDENT
                .build();

        String adminUserName = "sebasole11";
        String adminUserPassword = this.passwordEncoder.encode("password11");
        
        UserDetails sebaSoleUser = 
            User.builder()
                .username(adminUserName)
                .password(adminUserPassword)
                .roles(ADMIN.name())   // ROLE_ADMIN
                .build();

        
        
        return new InMemoryUserDetailsManager(
            facuConteUser,
            sebaSoleUser
        );
    }
    
    

}
