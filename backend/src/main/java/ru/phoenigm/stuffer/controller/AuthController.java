package ru.phoenigm.stuffer.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.phoenigm.stuffer.domain.form.RegistrationForm;
import ru.phoenigm.stuffer.exception.UserAlreadyExistsException;
import ru.phoenigm.stuffer.service.UserService;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;


    @PostMapping("/registration")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationForm form, BindingResult result) {
        log.info(form);

        if (result.hasErrors()) {
            Map<String, String> validationErrors = result.getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

            return ResponseEntity.badRequest().body(validationErrors);
        }

        try {
            /*return ResponseEntity.ok(userService.register(form));*/
            return ResponseEntity.created(URI.create("/api/profile"))
                    .body(userService.register(form));
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}


