package ru.phoenigm.stuffer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
@Slf4j
public class ProfileController {

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/profile")
    public UserDetails profile(Principal principal) {

        return userDetailsService.loadUserByUsername(principal.getName());
    }

}
