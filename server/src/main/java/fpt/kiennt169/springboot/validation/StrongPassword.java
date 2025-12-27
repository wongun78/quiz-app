package fpt.kiennt169.springboot.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Documented
@Constraint(validatedBy = {}) 
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotBlank(message = "{validation.password.notblank}")
@Size(min = 8, message = "{validation.password.size}")
@Pattern(regexp = ".*[A-Z].*", message = "{validation.password.uppercase}")
@Pattern(regexp = ".*[a-z].*", message = "{validation.password.lowercase}")
@Pattern(regexp = ".*\\d.*", message = "{validation.password.digit}")
@Pattern(regexp = ".*[!@#$%^&*()_+].*", message = "{validation.password.special}")
public @interface StrongPassword {
    String message() default "{validation.password.strong}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}