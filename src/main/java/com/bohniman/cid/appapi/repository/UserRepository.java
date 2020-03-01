package com.bohniman.cid.appapi.repository;

import java.util.Optional;

import com.bohniman.cid.appapi.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository
 */
public interface UserRepository  extends JpaRepository<User, Long>{

	User findByUsername(String username);
    Optional<User> findById(Long id);
    
}