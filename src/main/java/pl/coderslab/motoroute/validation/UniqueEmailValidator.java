package pl.coderslab.motoroute.validation;

import lombok.RequiredArgsConstructor;
import pl.coderslab.motoroute.dto.UserDto;
import pl.coderslab.motoroute.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, UserDto> {
    private final UserService userService;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext constraintValidatorContext) {
        if (userService.findByUserEmail(userDto.getEmail()) != null) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("{invalid.email.email-unique}")
                    .addPropertyNode("email")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
