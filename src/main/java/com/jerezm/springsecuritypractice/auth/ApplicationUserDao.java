package com.jerezm.springsecuritypractice.auth;

import java.util.Optional;

public interface ApplicationUserDao {
    
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
