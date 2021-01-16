package com.raju.accountserviceone.accountserviceone.config;

import com.raju.accountserviceone.accountserviceone.model.Account;
import com.raju.accountserviceone.accountserviceone.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitDb implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    public InitDb(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        accountRepository.deleteAll();

        accountRepository.save(new Account(1L, "1234567890", 50000, 1L));
        accountRepository.save(new Account(2L, "1234567891", 50000, 1L));
        accountRepository.save(new Account(3L, "1234567892", 50000, 1L));
        accountRepository.save(new Account(4L, "1234567893", 50000, 2L));
        accountRepository.save(new Account(5L, "1234567894", 50000, 2L));
        accountRepository.save(new Account(6L, "1234567895", 50000, 2L));
        accountRepository.save(new Account(7L, "1234567896", 50000, 3L));
        accountRepository.save(new Account(8L, "1234567897", 50000, 3L));
        accountRepository.save(new Account(9L, "1234567898", 50000, 3L));


    }
}
