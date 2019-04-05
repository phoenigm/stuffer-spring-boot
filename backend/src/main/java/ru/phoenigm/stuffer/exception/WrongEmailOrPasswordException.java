package ru.phoenigm.stuffer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Wrong email or password")
public class WrongEmailOrPasswordException extends RuntimeException {
}
