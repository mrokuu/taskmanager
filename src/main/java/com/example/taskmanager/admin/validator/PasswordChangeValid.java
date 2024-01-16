package com.example.taskmanager.admin.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordChangeValidator.class)
@Target({ ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordChangeValid {
    String message() default "Invalid password change";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}