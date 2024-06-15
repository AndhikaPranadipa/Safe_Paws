package com.EnigmaCamp.SafePaws.repository;

import com.EnigmaCamp.SafePaws.entity.AddressShelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressShelterRepository extends JpaRepository<AddressShelter, String> {
}
