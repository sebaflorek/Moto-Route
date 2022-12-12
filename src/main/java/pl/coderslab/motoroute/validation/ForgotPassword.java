package pl.coderslab.motoroute.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ForgotPasswordEmailValidator.class)
@Documented
public @interface ForgotPassword {

    String message() default "{invalid.email.email-notfound}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
