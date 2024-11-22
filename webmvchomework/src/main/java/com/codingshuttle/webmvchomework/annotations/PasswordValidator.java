package com.codingshuttle.webmvchomework.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.function.Predicate;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        Predicate<String> hasUppercase = s -> s.chars().anyMatch(Character::isUpperCase);
        Predicate<String> hasLowercase = s -> s.chars().anyMatch(Character::isLowerCase);
        Predicate<String> hasSpecialChar = s -> s.chars().anyMatch(c -> !Character.isLetterOrDigit(c));
        Predicate<String> hasMinLength = s -> s.length() >= 10;

        return hasUppercase.and(hasLowercase).and(hasSpecialChar).and(hasMinLength).test(value);
    }
}
