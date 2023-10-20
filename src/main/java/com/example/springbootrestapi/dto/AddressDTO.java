package com.example.springbootrestapi.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private Long addressId;
    private String street;
    private String city;
    private String state;
}
