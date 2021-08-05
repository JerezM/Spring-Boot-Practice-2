package com.jerezm.springsecuritypractice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // ADDED ONLY TO TEST SOMETHING -> REMOVE LATER
            .authorizeRequests()
            .antMatchers("/", "index").permitAll()            
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
                .authorities(STUDENT.getGrantedAuthorities())
                .build();

        String adminUserName = "sebasole11";
        String adminUserPassword = this.passwordEncoder.encode("password11");
        
        UserDetails sebaSoleUser = 
            User.builder()
                .username(adminUserName)
                .password(adminUserPassword)
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        String adminTraineeUserName = "dececco16";
        String adminTraineeUserPassword = this.passwordEncoder.encode("password16");

        UserDetails deCeccoUser = 
            User.builder()
                .username(adminTraineeUserName)
                .password(adminTraineeUserPassword)
                .authorities(ADMINTRAINEE.getGrantedAuthorities())
                .build();

        
        
        return new InMemoryUserDetailsManager(
            facuConteUser, // ROLE_STUDENT
            sebaSoleUser,  // ROLE_ADMIN
            deCeccoUser    // ROLE_ADMIN_TRAINEE
        );
    }
    
    

}
