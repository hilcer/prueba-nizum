package com.nizum.prueba.context;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    private final PasswordValidationProperties properties;

    private Pattern pattern;

    public PasswordValidator(PasswordValidationProperties properties) {
        this.properties = properties;
    }

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        pattern = Pattern.compile(properties.getPattern());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && pattern.matcher(value).matches();
    }
}

