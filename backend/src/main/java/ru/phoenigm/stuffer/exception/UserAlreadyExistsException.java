package ru.phoenigm.stuffer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User with this email already exists")
public class UserAlreadyExistsException extends RuntimeException {
    @Override
    public String getMessage() {
        return "User with this email already exists";
    }
}
