package com.bandg.users.models;

import com.bandg.users.dao.PostDao;
import com.bandg.users.security.ApplicationUserPermission;
import com.bandg.users.security.ApplicationUserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
/*
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
*/


import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.bandg.users.security.ApplicationUserRole.ADMIN;
import static com.bandg.users.security.ApplicationUserRole.REGULARUSER;

public class User  implements UserDetails {

    @JsonProperty("email")
    private final String email;
    @JsonProperty("fullName")
    private final String fullName;
    @JsonProperty("dob")
    private final Date dob;
    @JsonProperty("Authorities")
    private final Set<SimpleGrantedAuthority> authorities;

    @JsonProperty("imageId")
    private UUID imageId;

    private final String password;
    @JsonProperty("post")
    private final String   post;
    private  boolean isActivated;

    private  boolean isLocked;

    public User(
            @JsonProperty("email") String email,
            @JsonProperty("fullName")String fullName,
            @JsonProperty("dob")  Date dob,
            @JsonProperty("level")int level,
            @JsonProperty("password") String password,
            @JsonProperty("post") String post
    ) {

        this.email = email;
        this.fullName = fullName;
        this.dob = dob;
        this.authorities = REGULARUSER.getGrantedAuthorities();
        this.password = password;
        this.post = post;
        this.isActivated = false;
        this.isLocked = true;
    }

   @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {

        return isActivated;
    }
}
