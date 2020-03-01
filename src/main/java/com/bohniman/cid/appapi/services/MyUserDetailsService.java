package com.bohniman.cid.appapi.services;

import com.bohniman.cid.appapi.model.User;
import com.bohniman.cid.appapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserDetailsService
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return UserPrincipal.create(user);
    }

    @Transactional
    public User loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }

    // @Override
    // public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

    //     // Create User from database

    //     return new User("foo", "foo", new ArrayList<>());
    // }
}