package com.example.surfshop.service;

import com.example.surfshop.entities.Benutzer;
import com.example.surfshop.repositories.BenutzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: Tim Lueneburg
 * Date: 30.04.2021
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private BenutzerRepository benutzerRepository;

    @Value("${spring.security.user.name}")
    private String adminUserName;

    @Value("${spring.security.user.password}")
    private String adminPassword;

    @Value("${spring.security.user.roles}")
    private String adminRole;

    @Override
    public UserDetails loadUserByUsername(String benutzername) throws UsernameNotFoundException {
        Optional<Benutzer> benutzer = benutzerRepository.findByBenutzername(benutzername);

        if(benutzername.equals(adminUserName)) {
            return User.builder().username(adminUserName).password(adminPassword).roles(adminRole).build();
        } else if (benutzer.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(benutzer.get());
    }
}
