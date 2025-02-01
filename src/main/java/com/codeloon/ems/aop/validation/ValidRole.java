package com.codeloon.ems.aop.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RoleValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRole {
    String message() default "Invalid role. Allowed values: ADMIN, EMPLOYEE, CLIENT";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
