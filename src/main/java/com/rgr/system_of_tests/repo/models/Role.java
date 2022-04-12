package com.rgr.system_of_tests.repo.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,ADMIN,TESTER;
    @Override
    public String getAuthority(){
        return name();
    }
}
