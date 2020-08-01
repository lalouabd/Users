package com.bandg.users.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class User  implements UserDetails {

    @JsonProperty("email")
    private final String email;
    @JsonProperty("fullName")
    private final String fullName;
    @JsonProperty("dob")
    private final Date dob;

    @JsonProperty("Authorities")
    private final List<SimpleGrantedAuthority> authorities;
    @JsonProperty("imageLink")
    private final String imageLink;
    private final String password;
    @JsonProperty("postId")
    private final UUID  postId;

    private  boolean isActivated;

    private  boolean isLocked;

    public User(
            String email,
            String fullName,
            Date dob,
            List<SimpleGrantedAuthority> authorities,
            String imageLink,
            String password,
            UUID postId,
            boolean isActivated,
            boolean isLocked
    ) {

        this.email = email;
        this.fullName = fullName;
        this.dob = dob;
        this.authorities = authorities;
        this.imageLink = imageLink;
        this.password = password;
        this.postId = postId;
        this.isActivated = isActivated;
        this.isLocked = isLocked;
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
