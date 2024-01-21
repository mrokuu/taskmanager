package com.example.taskmanager.createTask.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MultipartFileValidator.class)
@Target({ ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface MultipartFileValid {
    String message() default "Invalid multipart file";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
