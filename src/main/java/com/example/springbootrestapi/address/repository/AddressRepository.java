package com.example.springbootrestapi.address.repository;

import com.example.springbootrestapi.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    Address save(Address address);
    Optional<Address> findByAddressId(Long id);
}
