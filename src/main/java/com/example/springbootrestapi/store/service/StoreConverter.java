package com.example.springbootrestapi.store.service;

import com.example.springbootrestapi.dto.AddressDTO;
import com.example.springbootrestapi.dto.StoreDTO;
import com.example.springbootrestapi.model.Address;
import com.example.springbootrestapi.model.Store;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StoreConverter {
    private final ModelMapper modelMapper;

    public StoreConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public StoreDTO mapToDTO(Store store) {
        return modelMapper.map(store, StoreDTO.class);
    }

    public Store mapToEntity(StoreDTO storeDTO) {
        return modelMapper.map(storeDTO, Store.class);
    }

    public AddressDTO mapToDTO(Address address) {
        return modelMapper.map(address, AddressDTO.class);
    }

    public Address mapToEntity(AddressDTO addressDTO) {
        return modelMapper.map(addressDTO, Address.class);
    }

    public void mapToEntity(StoreDTO storeDTO, Store store) {
        modelMapper.map(storeDTO, store);
    }

    public void mapToEntity(AddressDTO addressDTO, Address address) {
        modelMapper.map(addressDTO, address);
    }
}