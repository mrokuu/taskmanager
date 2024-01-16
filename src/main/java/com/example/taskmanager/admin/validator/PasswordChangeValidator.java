package com.example.taskmanager.admin.validator;

import com.example.taskmanager.admin.dto.request.UserRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordChangeValidator implements ConstraintValidator<PasswordChangeValid, UserRequestDTO> {

    @Override
    public void initialize(PasswordChangeValid passwordChangeValid) {
    }

    @Override
    public boolean isValid(UserRequestDTO appaUserRequestDTO, ConstraintValidatorContext ctx) {

        if (!appaUserRequestDTO.isPasswordChangeDetected()) {
            return true;
        }

        ctx.disableDefaultConstraintViolation();

        if (appaUserRequestDTO.getOldPassword() == null) {
            ctx.buildConstraintViolationWithTemplate("Old password is empty").addConstraintViolation();
            return false;
        } else if (!appaUserRequestDTO.getNewPassword().equals(appaUserRequestDTO.getNewRepeatedPassword())) {
            ctx.buildConstraintViolationWithTemplate("Passwords don't match").addConstraintViolation();
            return false;
        } else if (!isValidSize(appaUserRequestDTO)) {
            ctx.buildConstraintViolationWithTemplate("Password has incorrect length")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

    private boolean isValidSize(UserRequestDTO userRequestDTO) {
        return userRequestDTO.getNewPassword().length() >= 6
                && userRequestDTO.getNewPassword().length() <= 10;
    }
}
