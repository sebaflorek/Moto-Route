package pl.coderslab.motoroute.validation;

import pl.coderslab.motoroute.dto.UserCreateDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserCreateDto> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserCreateDto userCreateDto, ConstraintValidatorContext constraintValidatorContext) {
        if (!userCreateDto.getPassword().equals(userCreateDto.getMatchingPassword())) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("{invalid.password.confirm-password}")
                    .addPropertyNode("matchingPassword")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
