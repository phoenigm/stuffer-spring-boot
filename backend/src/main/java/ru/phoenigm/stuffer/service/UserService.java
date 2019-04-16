package ru.phoenigm.stuffer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.phoenigm.stuffer.domain.Role;
import ru.phoenigm.stuffer.domain.User;
import ru.phoenigm.stuffer.domain.form.ProfileUpdateForm;
import ru.phoenigm.stuffer.domain.form.RegistrationForm;
import ru.phoenigm.stuffer.exception.UserAlreadyExistsException;
import ru.phoenigm.stuffer.payload.Profile;
import ru.phoenigm.stuffer.repository.UserRepository;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User register(RegistrationForm form) {
        Optional<User> userCandidate = userRepository.findByEmail(form.getEmail());

        if (userCandidate.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        User user = User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .email(form.getEmail())
                .phoneNumber(form.getPhoneNumber())
                .password(passwordEncoder.encode(form.getPassword()))
                .roles(Collections.singleton(Role.USER))
                .registrationDate(LocalDateTime.now())
                .avatarUrl("https://pp.userapi.com/c848536/v848536366/131886/EYolGIZVWhg.jpg")
                .active(true)
                .build();
        return userRepository.save(user);
    }

    @Transactional
    public Optional<Profile> getProfileByUserId(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            Profile profile = Profile.fromUser(userOptional.get());
            profile.setDriverRating(reviewService.ratingByUserId(id) / 20.0);
            return Optional.of(profile);
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<Profile> getProfileByEmail(String email) {
        User user = (User) userDetailsService.loadUserByUsername(email);

        if (user != null) {
            Profile profile = Profile.fromUser(user);
            profile.setDriverRating(reviewService.ratingByUserId(user.getId()) / 20.0);
            return Optional.of(profile);
        }
        return Optional.empty();
    }

    public Optional<User> getByUserId(Long id) {
        return userRepository.findById(id);
    }

    public User update(ProfileUpdateForm form) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) userDetailsService.loadUserByUsername(principal.getName());

        currentUser.setFirstName(form.getFirstName());
        currentUser.setLastName(form.getLastName());
        currentUser.setPassword(form.getPassword());
        currentUser.setEmail(form.getEmail());
        currentUser.setPhoneNumber(form.getPhoneNumber());
        currentUser.setLastPasswordResetDate(new Date());

        return userRepository.save(currentUser);
    }
}
