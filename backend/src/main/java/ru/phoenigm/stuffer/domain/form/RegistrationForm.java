package ru.phoenigm.stuffer.domain.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.phoenigm.stuffer.service.PasswordMatches;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@PasswordMatches
public class RegistrationForm {
    @NotBlank(message = "{email.blank}")
    @Email(message = "{email.format}")
    private String email;

    @Size(min = 2, max = 50, message = "{firstName.size}")
    @NotBlank(message = "{firstName.blank}")
    @Pattern(regexp = "^([A-ZА-Я][a-za-я]*)$", message = "{firstName.format}")
    private String firstName;

    @Size(min = 2, max = 60, message = "{lastName.size}")
    @NotBlank(message = "{lastName.blank}")
    @Pattern(regexp = "^[A-ZА-Я][a-za-я]*$", message = "{lastName.format}")
    private String lastName;

    @NotBlank(message = "{phoneNumber.blank}")
    @Pattern(regexp = "^[+][0-9]{11}$", message = "{phoneNumber.format}")
    private String phoneNumber;

    @Size(min = 6, max = 30, message = "{password.size}")
    @NotBlank(message = "{password.blank}")
    private String password;

    @NotBlank(message = "{confirmedPassword.blank}")
    private String confirmedPassword;

    @AssertTrue(message = "{agreement.false}")
    private boolean agreement;
}
