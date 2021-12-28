/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author akhma
 */
public class AppUserDetail implements UserDetails{
    private User user;

    public AppUserDetail(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        
        String role = user.getRole().getName();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
        
        user.getRole().getRolePrivileges().forEach(rolePrivilege -> {
            String privilege = rolePrivilege.getPrivilege().getName().replace(' ', '_');
            authorities.add(new SimpleGrantedAuthority(privilege.toUpperCase()));
        });
        
        return authorities;
        
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !user.getIsAccountLocked();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.getIsAccountLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !user.getIsAccountLocked();
    }

    @Override
    public boolean isEnabled() {
        return !user.getIsAccountLocked();
    }
    
}
