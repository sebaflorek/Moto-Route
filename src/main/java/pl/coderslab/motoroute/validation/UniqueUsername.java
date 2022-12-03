package pl.coderslab.motoroute.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UniqueUsernameValidator.class, UniqueUsernameToEditValidator.class})
@Documented
public @interface UniqueUsername {
    String message() default "{invalid.username.username-unique}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
