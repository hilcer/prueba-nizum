package com.nizum.prueba.context;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface ValidPassword {
    String message() default "Contraseña inválida";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

