package com.jerezm.springsecuritypractice.security;

import java.util.Set;

import com.google.common.collect.Sets;

import static com.jerezm.springsecuritypractice.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    
    STUDENT( Sets.newHashSet() ),
    ADMIN(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE, COURSE_READ, COURSE_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    private ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

}
