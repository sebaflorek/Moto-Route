package pl.coderslab.motoroute.security;

import lombok.RequiredArgsConstructor;
import pl.coderslab.motoroute.dto.UserDto;
import pl.coderslab.motoroute.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, UserDto> {
    private final UserService userService;


    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext constraintValidatorContext) {
        return userService.findByUsername(userDto.getUsername()) == null;
    }
}
