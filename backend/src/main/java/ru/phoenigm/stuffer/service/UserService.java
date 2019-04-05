package ru.phoenigm.stuffer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.phoenigm.stuffer.domain.Role;
import ru.phoenigm.stuffer.domain.User;
import ru.phoenigm.stuffer.domain.form.RegistrationForm;
import ru.phoenigm.stuffer.exception.UserAlreadyExistsException;
import ru.phoenigm.stuffer.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
                .active(true)
                .build();
        return userRepository.save(user);
    }
}
