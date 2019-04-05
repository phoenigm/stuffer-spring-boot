package ru.phoenigm.stuffer.domain.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginForm {
    private String email;
    private String password;
    private boolean rememberMe;
}
