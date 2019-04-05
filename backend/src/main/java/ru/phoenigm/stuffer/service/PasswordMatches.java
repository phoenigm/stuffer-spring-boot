package ru.phoenigm.stuffer.service;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Repeatable(PasswordMatches.List.class)
public @interface PasswordMatches {
    String password() default "password";
    String confirmPassword() default "confirmedPassword";

    boolean showErrorOnConfirmPassword() default true;

    String message() default "{confirmedPassword.wrong}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    @Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        PasswordMatches[] value();
    }
}
