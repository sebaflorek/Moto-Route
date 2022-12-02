package pl.coderslab.motoroute.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueDayValidator.class)
@Documented
public @interface UniqueDayNumber {
    String message() default "{invalid.dayNumber.dayNumber-unique}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
