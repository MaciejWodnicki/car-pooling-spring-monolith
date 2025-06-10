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
        ValidationUtils.rejectIfEmpty(errors, "address.street", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "address.city", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "address.state", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "address.zip", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "address.country", "error.field.required");


    }
}
