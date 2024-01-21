package com.example.taskmanager.createTask.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class MultipartFileValidator implements ConstraintValidator<MultipartFileValid, MultipartFile> {

    @Override
    public void initialize(MultipartFileValid multipartFileValid) {
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext ctx) {
        return multipartFile != null;
    }

}
