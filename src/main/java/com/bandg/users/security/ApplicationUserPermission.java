package com.bandg.users.security;

public enum ApplicationUserPermission {
    READ("staff:read"),
    WRITE("staff:write");


    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
