package pl.coderslab.motoroute.validation;

import lombok.RequiredArgsConstructor;
import pl.coderslab.motoroute.dto.UserCreateDto;
import pl.coderslab.motoroute.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, UserCreateDto> {
    private final UserService userService;


    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserCreateDto userCreateDto, ConstraintValidatorContext constraintValidatorContext) {
        if (userService.findByUsername(userCreateDto.getUsername()) != null) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("{invalid.username.username-unique}")
                    .addPropertyNode("username")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
