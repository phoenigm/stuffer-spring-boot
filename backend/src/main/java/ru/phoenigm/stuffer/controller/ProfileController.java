package ru.phoenigm.stuffer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.phoenigm.stuffer.domain.User;
import ru.phoenigm.stuffer.payload.Profile;

import java.security.Principal;

@RestController
@RequestMapping("/api")
@Slf4j
public class ProfileController {

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/profile")
    public Profile profile(Principal principal) {
        User user = (User) userDetailsService.loadUserByUsername(principal.getName());

        return Profile.fromUser(user);
    }

}
