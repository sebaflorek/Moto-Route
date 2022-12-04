package pl.coderslab.motoroute.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import pl.coderslab.motoroute.dto.UserPassDto;
import pl.coderslab.motoroute.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@RequiredArgsConstructor
public class ChangePasswordValidator implements ConstraintValidator<ChangePassword, UserPassDto> {
    private final UserService userService;

    @Override
    public void initialize(ChangePassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserPassDto userPassDto, ConstraintValidatorContext constraintValidatorContext) {
        String actualPassword = userService.findById(userPassDto.getId()).getPassword();
        if (!BCrypt.checkpw(userPassDto.getOldPassword(), actualPassword)) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("{invalid.password.old-password}")
                    .addPropertyNode("oldPassword")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
