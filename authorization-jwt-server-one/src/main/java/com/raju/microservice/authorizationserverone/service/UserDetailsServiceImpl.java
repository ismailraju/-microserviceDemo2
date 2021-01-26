package com.raju.microservice.authorizationserverone.service;

import com.raju.microservice.authorizationserverone.model.AuthUserDetails;
import com.raju.microservice.authorizationserverone.model.User;
import com.raju.microservice.authorizationserverone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findByUsername(name);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username or Password wrong."));
        UserDetails userDetails = new AuthUserDetails(optionalUser.get());

        //user expried statuses check
        new AccountStatusUserDetailsChecker().check(userDetails);
        return userDetails;
    }
}
