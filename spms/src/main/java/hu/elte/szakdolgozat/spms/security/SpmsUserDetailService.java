/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.szakdolgozat.spms.security;

import hu.elte.szakdolgozat.spms.model.User;
import hu.elte.szakdolgozat.spms.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Blanka Orosz
 */
@Service
public class SpmsUserDetailService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> result = userRepository.findByName(name);
        
        if (result.isPresent()) {
            return new SpmsUserPrincipal(result.get());
        }
        
        return null;
    }
}