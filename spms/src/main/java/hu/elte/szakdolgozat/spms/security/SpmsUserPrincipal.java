/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szakdolgozat.spms.security;

import hu.elte.szakdolgozat.spms.model.entity.User;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author Blanka Orosz
 */
public class SpmsUserPrincipal implements UserDetails{
    
    private User user;
    private Collection<GrantedAuthority> userAuthorities;
    
    public SpmsUserPrincipal(User user) {
        this.user = user;
        
        if (user.getRole() != null && !CollectionUtils.isEmpty(user.getRole().getRights())) {
            userAuthorities = new ArrayList<>(user.getRole().getRights().size() + 1);

            userAuthorities.add(new SimpleGrantedAuthority("ROLE_" +
                    user.getRole().getName().name()));

            user.getRole().getRights().forEach((right) -> {
                userAuthorities.add(new SimpleGrantedAuthority(right.getName().name()));
            });
        }
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return user;
    }
    
}
