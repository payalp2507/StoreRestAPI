package com.example.springbootrestapi.store.service;

import com.example.springbootrestapi.dto.StoreDTO;

public interface StoreDetailService {

     void create(StoreDTO storeDTO);
     StoreDTO getStoreById(Long storeId);
     StoreDTO getStoreByUserId(String userId);

     void updateStore(Long storeId,StoreDTO updatedStoreDTO);
}
