package com.EnigmaCamp.SafePaws.repository;

import com.EnigmaCamp.SafePaws.entity.AddressUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressUserRepository extends JpaRepository<AddressUser, String> {
}
