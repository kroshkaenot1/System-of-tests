package com.rgr.system_of_tests.models;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    USER,ADMIN,TESTER;
    @Override
    public String getAuthority(){
        return name();
    }
}
