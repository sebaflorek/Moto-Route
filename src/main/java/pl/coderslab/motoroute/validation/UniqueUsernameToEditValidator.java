package pl.coderslab.motoroute.validation;

import lombok.RequiredArgsConstructor;
import pl.coderslab.motoroute.dto.UserEditDto;
import pl.coderslab.motoroute.entity.User;
import pl.coderslab.motoroute.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueUsernameToEditValidator implements ConstraintValidator<UniqueUsername, UserEditDto> {
    private final UserService userService;


    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserEditDto userEditDto, ConstraintValidatorContext constraintValidatorContext) {
        User checkUser = userService.findByUsername(userEditDto.getUsername());
        if (checkUser != null && checkUser.getId() != userEditDto.getId()) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("{invalid.username.username-unique}")
                    .addPropertyNode("username")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
