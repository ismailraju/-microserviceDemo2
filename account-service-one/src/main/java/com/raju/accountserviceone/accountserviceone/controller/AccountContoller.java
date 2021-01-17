package com.raju.accountserviceone.accountserviceone.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raju.accountserviceone.accountserviceone.model.Account;
import com.raju.accountserviceone.accountserviceone.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AccountContoller {


    public static final Logger LOGGER = LoggerFactory.getLogger(AccountContoller.class);

    private ObjectMapper objectMapper = new ObjectMapper();
    private AccountRepository accountRepository;

    public AccountContoller(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostMapping
    public Account add(@RequestBody Account account) {
        return accountRepository.save(account);
    }

    @PutMapping
    public Account update(@RequestBody Account account) {
        return accountRepository.save(account);
    }


    @PutMapping(value = "/withdraw/{id}/{amount}")
    public Account withdraw(@PathVariable("id") Long id, @PathVariable("amount") int amount) throws Exception {
        Optional<Account> acnt = accountRepository.findById(id);
        if (acnt.isPresent()) {
            Account account = acnt.get();
            LOGGER.info("Account found : {}" + objectMapper.writeValueAsString(account));

            account.setBalance(account.getBalance() - amount);
            LOGGER.info("Current balance : {}", objectMapper.writeValueAsString(Collections.singletonMap("balance", account.getBalance())));

            return accountRepository.save(account);

        } else {
            LOGGER.info("Account not found : {}", objectMapper.writeValueAsString(Collections.singletonMap("id", id)));
            throw new Exception("Account not found");
        }

    }


    @GetMapping("/customer/{customerId}")
    public List<Account> findByCustomerId(@PathVariable("customerId") Long customerId) {
        return accountRepository.findBycustomerId(customerId);
    }


    @GetMapping(value = "/{id}")
    public Account get(@PathVariable("id") Long id) throws Exception {
        Optional<Account> acnt = accountRepository.findById(id);
        if (acnt.isPresent()) {
            return acnt.get();
        } else {
            throw new Exception("Account not found ");
        }
    }


    @PostMapping(value = "/ids")
    public List<Account> getMultiple(@RequestBody List<Long> ids) {
        return accountRepository.findAllById(ids);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {


        accountRepository.deleteById(id);
    }

}
