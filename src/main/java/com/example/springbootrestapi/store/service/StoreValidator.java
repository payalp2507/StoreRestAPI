package com.example.springbootrestapi.store.service;

import com.example.springbootrestapi.address.AddressValidator;
import com.example.springbootrestapi.dto.StoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class StoreValidator implements Validator {
    private final AddressValidator addressValidator;

    @Autowired
    public StoreValidator(AddressValidator addressValidator) {
        this.addressValidator = addressValidator;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return StoreDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        StoreDTO storeDTO = (StoreDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "storeStatus", "field.required", "Store status is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userID", "field.required", "User ID is required.");
        ValidationUtils.rejectIfEmpty(errors, "name", "field.required", "Name is required.");
        ValidationUtils.rejectIfEmpty(errors, "title", "field.required", "Title is required.");
        ValidationUtils.rejectIfEmpty(errors, "email", "field.required", "Email is required.");
        ValidationUtils.rejectIfEmpty(errors, "iconPath", "field.required", "Icon path is required.");
        ValidationUtils.rejectIfEmpty(errors, "announcementTitle", "field.required", "Announcement title is required.");
        ValidationUtils.rejectIfEmpty(errors, "storyTitle", "field.required", "Story title is required.");

        if (storeDTO.getStoreAddress() != null) {
            addressValidator.validate(storeDTO.getStoreAddress(), errors);
        }
        if (!isValidEmail(storeDTO.getEmail())) {
            errors.rejectValue("email", "field.invalidEmail", "Invalid email address");
        }
        validateFieldLength(storeDTO.getName(), "name", 50, errors);
        validateFieldLength(storeDTO.getTitle(), "title", 100, errors);
        validateFieldLength(storeDTO.getEmail(), "email", 50, errors);
        validateFieldLength(storeDTO.getStoryTitle(), "storyTitle", 80, errors);
        validateFieldLength(storeDTO.getAnnouncementTitle(), "announcementTitle", 50, errors);
        validateFieldLength(storeDTO.getMessageToBuyers(), "messageToBuyer", 100, errors);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void validateFieldLength(String fieldValue, String fieldName, int maxLength, Errors errors) {
        if (fieldValue.length() > maxLength) {
            errors.rejectValue(fieldName, "field.length", new Object[]{maxLength}, "The length of the field must be between 0 to " + maxLength + " characters.");
        }
    }
}