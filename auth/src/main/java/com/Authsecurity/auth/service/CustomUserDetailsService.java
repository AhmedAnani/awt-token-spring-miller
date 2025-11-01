package com.Authsecurity.auth.service;

import com.Authsecurity.auth.model.UserModle;
import com.Authsecurity.auth.repositry.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepository; // your JPA repo

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModle user = userRepository.findByEmail(username);
               // .(() -> new UsernameNotFoundException("User not found: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>()
        );
    }
}