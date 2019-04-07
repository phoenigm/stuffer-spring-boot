package ru.phoenigm.stuffer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.phoenigm.stuffer.domain.form.RegistrationForm;
import ru.phoenigm.stuffer.exception.UserAlreadyExistsException;
import ru.phoenigm.stuffer.service.UserService;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;


    @PostMapping("/registration")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationForm form, BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> validationErrors = new HashMap<>();

            for (FieldError error : result.getFieldErrors()) {
                if (validationErrors.containsKey(error.getField())) {
                    continue;
                }
                validationErrors.put(error.getField(), error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(validationErrors);
        }

        try {
            return ResponseEntity.created(URI.create("/api/profile"))
                    .body(userService.register(form));
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        return null;
    }
}


