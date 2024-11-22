package com.codingshuttle.webmvchomework.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.stream.IntStream;

public class PrimeNumberValidator implements ConstraintValidator<PrimeNumber, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null || value < 2) {
            return false;
        }
        return IntStream.rangeClosed(2, (int) Math.sqrt(value))
                .noneMatch(i -> value % i == 0);
    }
}
