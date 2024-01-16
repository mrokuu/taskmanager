package com.example.taskmanager.admin.validator;

import com.example.taskmanager.admin.dto.request.UserRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordCreateValidator implements ConstraintValidator<PasswordCreateValid, UserRequestDTO> {

    @Override
    public void initialize(PasswordCreateValid passwordCreateValid) {
    }

    @Override
    public boolean isValid(UserRequestDTO userRequestDTO, ConstraintValidatorContext ctx) {

        ctx.disableDefaultConstraintViolation();

        if (!userRequestDTO.getNewPassword().equals(userRequestDTO.getNewRepeatedPassword())) {
            ctx.buildConstraintViolationWithTemplate("Passwords don't match").addConstraintViolation();
            return false;
        } else if (!isValidSize(userRequestDTO)) {
            ctx.buildConstraintViolationWithTemplate("Password has incorrect length")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

    private boolean isValidSize(UserRequestDTO appaUserRequestDTO) {
        return appaUserRequestDTO.getNewPassword().length() >= 6
                && appaUserRequestDTO.getNewPassword().length() <= 10;
    }
}