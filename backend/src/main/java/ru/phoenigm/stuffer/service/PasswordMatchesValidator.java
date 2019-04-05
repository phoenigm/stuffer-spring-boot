package ru.phoenigm.stuffer.service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Objects;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    private PasswordMatches config;

    @Override
    public void initialize(PasswordMatches config) {
        this.config = config;
    }

    @Override
    public boolean isValid(Object passwordDto, ConstraintValidatorContext context) {
        boolean isValid = isValid(passwordDto, config.password(), config.confirmPassword());

        if (!isValid && config.showErrorOnConfirmPassword()) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode(config.confirmPassword()).addConstraintViolation();
        }

        return isValid;
    }

    private boolean isValid(Object passwordDto, String password, String confirmPassword) {
        try {
            Field passwordField = passwordDto.getClass().getDeclaredField(password);
            Field confirmPasswordField = passwordDto.getClass().getDeclaredField(confirmPassword);
            passwordField.setAccessible(true);
            confirmPasswordField.setAccessible(true);
            Object passwordValue = passwordField.get(passwordDto);
            Object confirmPasswordValue = confirmPasswordField.get(passwordDto);
            passwordField.setAccessible(false);
            confirmPasswordField.setAccessible(false);
            return Objects.equals(passwordValue, confirmPasswordValue);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            return false;
        }
    }
}
