package com.codeloon.ems.aop.validation;

import com.codeloon.ems.dto.Rol;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class RoleValidator implements ConstraintValidator<ValidRole, Rol> {
    @Override
    public boolean isValid(Rol role, ConstraintValidatorContext context) {
        return role != null && Arrays.asList(Rol.values()).contains(role);
    }
}
