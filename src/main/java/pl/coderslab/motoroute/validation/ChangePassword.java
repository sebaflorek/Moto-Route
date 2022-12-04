package pl.coderslab.motoroute.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ChangePasswordValidator.class)
@Documented
public @interface ChangePassword {
    String message() default "{invalid.password.old-password}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
