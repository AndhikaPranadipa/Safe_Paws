package com.EnigmaCamp.SafePaws.repository;

import com.EnigmaCamp.SafePaws.entity.AddressShelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressShelterRepository extends JpaRepository<AddressShelter, String> {
    List<AddressShelter> findByShelterId(String shelterId);
}
