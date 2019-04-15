package ru.phoenigm.stuffer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import ru.phoenigm.stuffer.domain.User;
import ru.phoenigm.stuffer.domain.form.ProfileUpdateForm;
import ru.phoenigm.stuffer.payload.Profile;
import ru.phoenigm.stuffer.service.UserService;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class ProfileController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public Profile profile(Principal principal) {
        User user = (User) userDetailsService.loadUserByUsername(principal.getName());

        return Profile.fromUser(user);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<Profile> anotherProfile(@PathVariable Long id) {
        Optional<User> userCandidate = userService.findById(id);

        if (userCandidate.isPresent()) {
            return ResponseEntity.ok(Profile.fromUser(userCandidate.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/profile")
    public ResponseEntity<Profile> update(ProfileUpdateForm form) {
        return ResponseEntity.ok(Profile.fromUser(userService.update(form)));
    }

}
