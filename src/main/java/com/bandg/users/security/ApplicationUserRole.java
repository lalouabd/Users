package com.bandg.users.security;

import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.nio.channels.WritableByteChannel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.bandg.users.security.ApplicationUserPermission.READ;
import static com.bandg.users.security.ApplicationUserPermission.WRITE;
public enum ApplicationUserRole {
    ADMIN(Sets.newHashSet(Lists.list(READ,WRITE))),
    REGULARUSER(Sets.newHashSet(Lists.list(READ)));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
