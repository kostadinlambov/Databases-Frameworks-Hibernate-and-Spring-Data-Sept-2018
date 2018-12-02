package com.kl.productshop.util.validation;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidatorUtil {
    <E> boolean isValid(E object);

    <E> Set<ConstraintViolation<E>> violations(E object);
}
