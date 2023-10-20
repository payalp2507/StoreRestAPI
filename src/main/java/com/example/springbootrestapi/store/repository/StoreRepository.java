package com.example.springbootrestapi.store.repository;


import com.example.springbootrestapi.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    Store save(Store store);

    Optional<Store> findByStoreId(Long aLong);

    Optional<Store> findByUserID(String string);
}
