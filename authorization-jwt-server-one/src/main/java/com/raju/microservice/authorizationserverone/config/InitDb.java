package com.raju.microservice.authorizationserverone.config;

import com.raju.microservice.authorizationserverone.model.Permission;
import com.raju.microservice.authorizationserverone.model.Role;
import com.raju.microservice.authorizationserverone.model.User;
import com.raju.microservice.authorizationserverone.repository.PermissionRepository;
import com.raju.microservice.authorizationserverone.repository.RoleRepository;
import com.raju.microservice.authorizationserverone.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Configuration
public class InitDb implements CommandLineRunner {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PermissionRepository permissionRepository;
    private PasswordEncoder passwordEncoder;

    public InitDb(UserRepository userRepository, RoleRepository roleRepository, PermissionRepository permissionRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {
        permissionRepository.deleteAll();
        roleRepository.deleteAll();
        userRepository.deleteAll();


        Permission create_profile = Permission.builder().name("create_profile").build();
        Permission read_profile = Permission.builder().name("read_profile").build();
        Permission update_profile = Permission.builder().name("update_profile").build();
        Permission delete_profile = Permission.builder().name("delete_profile").build();

        //permission save
        permissionRepository.save(create_profile);
        permissionRepository.save(read_profile);
        permissionRepository.save(update_profile);
        permissionRepository.save(delete_profile);


        Role role_admin = Role.builder().name("ROLE_admin")
                .permissions(Arrays.asList(create_profile,read_profile,update_profile,delete_profile))
                .build();
        Role role_operator = Role.builder().name("ROLE_operator")
                .permissions(Arrays.asList(read_profile,update_profile))
                .build();
        //Role save
        roleRepository.save(role_admin);
        roleRepository.save(role_operator);


        User admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .email("admin@admin.com")
                .enabled(true)
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .accountNonLocked(true)
                .roles(Arrays.asList(role_admin))
                .build();

        User user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                .email("user@user.com")
                .enabled(true)
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .accountNonLocked(true)
                .roles(Arrays.asList(role_operator))
                .build();

        //save Users
        userRepository.saveAll(Arrays.asList(admin,user));

    }
}
