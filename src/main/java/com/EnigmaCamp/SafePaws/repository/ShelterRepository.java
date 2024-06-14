package com.EnigmaCamp.SafePaws.repository;

import com.EnigmaCamp.SafePaws.entity.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, String> {

    boolean existsByEmail(String email);

    Optional<Shelter> findByEmail(String email);
}
