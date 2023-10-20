package com.example.springbootrestapi.address;

import com.example.springbootrestapi.dto.AddressDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AddressValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return AddressDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AddressDTO addressDTO = (AddressDTO) target;

        ValidationUtils.rejectIfEmpty(errors, "storeAddress.street","field.required", "Street is required.");
        ValidationUtils.rejectIfEmpty(errors, "storeAddress.city"  ,"field.required", "City is required.");
        ValidationUtils.rejectIfEmpty(errors, "storeAddress.state" ,"field.required", "State is required.");

        validateFieldLength(addressDTO.getStreet(), "storeAddress.street", 100, errors);
        validateFieldLength(addressDTO.getCity()  , "storeAddress.city"  , 50, errors);
        validateFieldLength(addressDTO.getState() , "storeAddress.state" , 50, errors);

    }

    private void validateFieldLength(String fieldValue, String fieldName, int maxLength, Errors errors) {
        if (fieldValue.length() > maxLength) {
            errors.rejectValue(fieldName, "field.length", new Object[]{maxLength}, "The length of the field must be between 0 to " + maxLength + " characters.");
        }
    }

}
