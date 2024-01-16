package com.example.taskmanager.admin.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordCreateValidator.class)
@Target({ ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordCreateValid {
    String message() default "Invalid password create";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}