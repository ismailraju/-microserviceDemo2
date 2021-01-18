package com.raju.microservice.authorizationserverone.repository;

import com.raju.microservice.authorizationserverone.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,Integer> {
}
