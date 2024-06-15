package com.EnigmaCamp.SafePaws.repository;

import com.EnigmaCamp.SafePaws.entity.AddressCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressCustomerRepository extends JpaRepository<AddressCustomer, String> {
}
