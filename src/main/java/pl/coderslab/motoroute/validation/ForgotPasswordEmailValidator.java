package pl.coderslab.motoroute.validation;

import lombok.RequiredArgsConstructor;
import pl.coderslab.motoroute.dto.UserForgotPassDto;
import pl.coderslab.motoroute.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class ForgotPasswordEmailValidator implements ConstraintValidator<ForgotPassword, UserForgotPassDto> {

    private final UserService userService;

    @Override
    public void initialize(ForgotPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserForgotPassDto userForgotPassDto, ConstraintValidatorContext constraintValidatorContext) {
        if (userService.findByUserEmail(userForgotPassDto.getEmail()) == null) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("{invalid.email.email-notfound}")
                    .addPropertyNode("email")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
