package pl.coderslab.motoroute.validation;

import pl.coderslab.motoroute.dto.UserResetPassDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ResetPasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserResetPassDto> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserResetPassDto userResetPassDto, ConstraintValidatorContext constraintValidatorContext) {
        if (!userResetPassDto.getNewPassword().equals(userResetPassDto.getMatchingNewPassword())) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("{invalid.password.confirm-password}")
                    .addPropertyNode("matchingNewPassword")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
