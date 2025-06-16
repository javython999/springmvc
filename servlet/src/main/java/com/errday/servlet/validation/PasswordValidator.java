package com.errday.servlet.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;


public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    private int minLength;

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        this.minLength = constraintAnnotation.minLength();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {

        if (!StringUtils.hasText(password)) {
            return false;
        }

        boolean upperCase = password.chars().anyMatch(Character::isUpperCase);
        boolean lowerCase = password.chars().anyMatch(Character::isLowerCase);
        boolean digit = password.chars().anyMatch(Character::isDigit);

        return password.length() >= minLength && upperCase && lowerCase && digit;
    }
}
