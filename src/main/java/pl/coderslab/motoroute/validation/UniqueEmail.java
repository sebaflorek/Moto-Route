package pl.coderslab.motoroute.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
@Documented
public @interface UniqueEmail {
    String message() default "{invalid.email.email-unique}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
