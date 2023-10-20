package com.example.springbootrestapi.store.service;

import com.example.springbootrestapi.address.repository.AddressRepository;
import com.example.springbootrestapi.common.exception.ItemNotFoundException;
import com.example.springbootrestapi.dto.StoreDTO;
import com.example.springbootrestapi.model.Address;
import com.example.springbootrestapi.model.Store;
import com.example.springbootrestapi.store.repository.StoreRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class StoreDetailServiceImpl implements StoreDetailService {

    private final StoreRepository storeRepository;
    private final StoreConverter storeConverter;
    private final AddressRepository addressRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public StoreDetailServiceImpl(StoreRepository storeRepository, StoreConverter storeConverter, AddressRepository addressRepository, ModelMapper modelMapper) {
        this.storeRepository = storeRepository;
        this.storeConverter = storeConverter;
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(StoreDTO storeDTO) {
        Store store = storeConverter.mapToEntity(storeDTO);
        storeRepository.save(store);
    }

    @Override
    public StoreDTO getStoreById(Long storeId) {
        Optional<Store> storeOptional = storeRepository.findByStoreId(storeId);
        if (!storeOptional.isPresent()) {
            throw new ItemNotFoundException("Store with ID " + storeId + " not found");
        }
        return storeConverter.mapToDTO(storeOptional.get());
    }

    @Override
    public StoreDTO getStoreByUserId(String userId) {
        Optional<Store> storeOptional = storeRepository.findByUserID(userId);
        if (!storeOptional.isPresent()) {
            throw new ItemNotFoundException("User ID " + userId + " not found");
        }
        return storeConverter.mapToDTO(storeOptional.get());
    }

    @Override
    public void updateStore(Long storeId, StoreDTO updatedStoreDTO) throws ItemNotFoundException{
        Optional<Store> storeOptional = storeRepository.findByStoreId(storeId);

        if (!storeOptional.isPresent()) {
            throw new ItemNotFoundException("Store with ID " + storeId + " not found");
        } else {
            Store store = storeOptional.get();
            Address existingAddress = addressRepository.findByAddressId(store.getAddress().getAddressId()).get();
            storeConverter.mapToEntity(updatedStoreDTO, store);
            storeConverter.mapToEntity(updatedStoreDTO.getStoreAddress(), existingAddress);
     storeRepository.save(store);
        }

    }

}
