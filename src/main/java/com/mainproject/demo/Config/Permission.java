package com.mainproject.demo.Config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@RequiredArgsConstructor
public enum Permission {
    USER_READ("users:read"),
    USER_UPDATE("users:update"),
    USER_CREATE("users:create"),
    USER_DELETE("users:delete"),
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete");


    @Getter
    private final String permission;


}
