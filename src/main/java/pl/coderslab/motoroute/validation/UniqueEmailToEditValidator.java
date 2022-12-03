package pl.coderslab.motoroute.validation;

import lombok.RequiredArgsConstructor;
import pl.coderslab.motoroute.dto.UserEditDto;
import pl.coderslab.motoroute.entity.User;
import pl.coderslab.motoroute.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueEmailToEditValidator implements ConstraintValidator<UniqueEmail, UserEditDto> {
    private final UserService userService;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserEditDto userEditDto, ConstraintValidatorContext constraintValidatorContext) {
        User checkUser = userService.findByUserEmail(userEditDto.getEmail());
        if (checkUser != null && checkUser.getId() != userEditDto.getId()) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("{invalid.email.email-unique}")
                    .addPropertyNode("email")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
