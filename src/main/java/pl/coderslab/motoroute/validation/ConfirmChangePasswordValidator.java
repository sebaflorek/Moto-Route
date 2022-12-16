package pl.coderslab.motoroute.validation;

import pl.coderslab.motoroute.dto.UserPassDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmChangePasswordValidator implements ConstraintValidator<PasswordMatches, UserPassDto> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserPassDto userPassDto, ConstraintValidatorContext constraintValidatorContext) {
        if (!userPassDto.getNewPassword().equals(userPassDto.getMatchingNewPassword())) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("{invalid.password.confirm-password}")
                    .addPropertyNode("matchingNewPassword")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
