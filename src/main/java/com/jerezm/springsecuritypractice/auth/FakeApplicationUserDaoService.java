package com.jerezm.springsecuritypractice.auth;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import static com.jerezm.springsecuritypractice.security.ApplicationUserRole.*;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        Optional<ApplicationUser> appUserOptional =
            this.getApplicationUsers().stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();

        return appUserOptional;
    }
    
    /**
     * This simulates the communication with the database
     * @return a list of all the application users.
     */
    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = List.of(
            new ApplicationUser(
                STUDENT.getGrantedAuthorities(),
                this.passwordEncoder.encode("password7"),
                "facuconte7",
                true,
                true,
                true,
                true
            ),
            new ApplicationUser(
                ADMIN.getGrantedAuthorities(),
                this.passwordEncoder.encode("password11"),
                "sebasole11",
                true,
                true,
                true,
                true
            ),
            new ApplicationUser(
                ADMINTRAINEE.getGrantedAuthorities(),
                this.passwordEncoder.encode("password16"),
                "dececco16",
                true,
                true,
                true,
                true
            )
        );

        return applicationUsers;
    }
}
