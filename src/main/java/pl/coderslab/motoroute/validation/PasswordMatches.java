package pl.coderslab.motoroute.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PasswordMatchesValidator.class, ConfirmChangePasswordValidator.class, ResetPasswordMatchesValidator.class})
@Documented
public @interface PasswordMatches {
    String message() default "{invalid.password.confirm-password}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
