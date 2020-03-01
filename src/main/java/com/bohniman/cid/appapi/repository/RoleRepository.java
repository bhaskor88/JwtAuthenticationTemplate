package com.bohniman.cid.appapi.repository;

import com.bohniman.cid.appapi.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RoleRepository
 */
public interface RoleRepository extends JpaRepository<Role, Long>{

    
}