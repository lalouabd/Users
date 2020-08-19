package com.bandg.users.models;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("gender")
    private final Gender gender;

    private  boolean isActivated;
    private  boolean isLocked;

    public User(
            @JsonProperty("email") String email,
            @JsonProperty("fullName") String fullName,
            @JsonProperty("dob") Date dob,
            @JsonProperty("password") String password,
            @JsonProperty("post") String post,
            @JsonProperty("gender") Gender gender) {

        this.email = email;
        this.fullName = fullName;
        this.dob = dob;
        this.gender = gender;
        this.authorities = REGULARUSER.getGrantedAuthorities();
        this.password = password;
        this.post = post;
        if (this.gender.equals(Gender.FEMALE))
            this.imageId = UUID.fromString("53eaf05e-11c6-40c7-b5ea-6337d4583e43");
        else
            this.imageId = UUID.fromString("358053d2-63ec-4c5d-9363-baa8f15c2a9f");

        this.isActivated = false;
        this.isLocked = true;
    }
    public User(
             String email,
           String fullName,
            Date dob,
             String password,
            String post,
           Gender gender,
             int isActivated,
             int isLocked,
             int isAdmin,
             UUID imageId) {

        this.email = email;
        this.fullName = fullName;
        this.dob = dob;
        this.gender = gender;
        this.authorities = isAdmin == 1 ?
                REGULARUSER.getGrantedAuthorities():
                ADMIN.getGrantedAuthorities();
        this.password = password;
        this.post = post;
        this.imageId = imageId;
        this.isActivated = isActivated == 1;
        this.isLocked = isLocked == 1;

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
