package com.raju.microservice.authorizationserverone.repository;

import com.raju.microservice.authorizationserverone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String name);
}
