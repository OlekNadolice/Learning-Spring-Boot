package com.example.SpringNauka.repositories;

import com.example.SpringNauka.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}