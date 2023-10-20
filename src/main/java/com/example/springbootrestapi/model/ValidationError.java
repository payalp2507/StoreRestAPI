package com.example.springbootrestapi.model;

import lombok.*;

@AllArgsConstructor
@Data
public class ValidationError {
    private String field;
    private String message;
}
