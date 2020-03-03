package com.bohniman.cid.appapi.services;

/**
 * UserService
 */

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.bohniman.cid.appapi.exception.CommonException;
import com.bohniman.cid.appapi.model.Role;
import com.bohniman.cid.appapi.model.User;
import com.bohniman.cid.appapi.repository.RoleRepository;
import com.bohniman.cid.appapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * UserService
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User newUser) {
        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

            // Username has to be unique (Custom Exception is required)

            // Find if role exist, if not throw exception
            Set<Role> roleList = new HashSet<Role>();
            if (null == newUser.getRoles())
                throw new CommonException("Empty role list provided");
            for (Role r : newUser.getRoles()) {
                Optional<Role> foundRole = roleRepository.findById(r.getRoleid());
                if (!foundRole.isPresent())
                    throw new CommonException("Role not Found");
                roleList.add(foundRole.get());
            }
            if (roleList.size() == 0) {
                throw new CommonException("Empty role list provided");
            }
            // Password and Confirmpassword match
            // Not to persist confirmpassword
            newUser.setRoles(roleList);
            return userRepository.save(newUser);
        } catch (CommonException e) {
            throw new CommonException(e.getMessage());
        } catch (IllegalArgumentException | InvalidDataAccessApiUsageException | NullPointerException e) {
            throw new CommonException("Some error has ocurred ! Please verify the JSON format.");
        } catch (Exception e) {
            throw new CommonException("Username already exist");
        }

    }

    // public User getUser(User newUser) {

    // return userRepository.findById();
    // }
}