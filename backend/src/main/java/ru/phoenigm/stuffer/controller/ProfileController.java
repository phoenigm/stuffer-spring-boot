package ru.phoenigm.stuffer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    private UserService userService;

    @GetMapping("/profile")
    public Profile profile(Principal principal) {
        return userService.getProfileByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Profile not found  "));
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<Profile> anotherProfile(@PathVariable Long id) {
        Optional<Profile> userCandidate = userService.getProfileByUserId(id);

        if (userCandidate.isPresent()) {
            return ResponseEntity.ok(userCandidate.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/profile")
    public ResponseEntity<Profile> update(ProfileUpdateForm form) {
        return ResponseEntity.ok(Profile.fromUser(userService.update(form)));
    }

    @PutMapping("/profile")
    public ResponseEntity<?> uploadAvatar(@RequestParam("avatar") MultipartFile file, Principal principal) {
        return ResponseEntity.ok(userService.uploadAvatar(principal.getName(), file));
    }

}
