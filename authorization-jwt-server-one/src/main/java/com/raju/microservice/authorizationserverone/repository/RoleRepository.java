package com.raju.microservice.authorizationserverone.repository;

import com.raju.microservice.authorizationserverone.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository  extends JpaRepository<Role,Integer> {
}
