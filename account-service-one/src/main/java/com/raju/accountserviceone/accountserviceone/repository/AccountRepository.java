package com.raju.accountserviceone.accountserviceone.repository;

import com.raju.accountserviceone.accountserviceone.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findBycustomerId(Long customerId);
}
