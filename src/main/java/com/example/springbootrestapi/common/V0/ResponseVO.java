package com.example.springbootrestapi.common.V0;

import com.example.springbootrestapi.model.ValidationError;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class ResponseVO<T> {
    private String successMessage;
    private String errorMessage;
    private List<ValidationError> validationErrors;
    private T data;

    public void addValidationError(String field, String message) {
        if (validationErrors == null) {
            validationErrors = new ArrayList<>();
        }
         validationErrors.add(new ValidationError(field, message));
    }
 }
