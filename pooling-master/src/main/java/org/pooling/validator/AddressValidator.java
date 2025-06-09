package org.pooling.validator;

import org.pooling.domain.user.Address;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class AddressValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Address.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "street", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "city", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "state", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "zip", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "country", "error.field.required");


    }
}
