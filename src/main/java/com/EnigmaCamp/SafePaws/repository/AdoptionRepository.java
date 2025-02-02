package com.EnigmaCamp.SafePaws.repository;

import java.util.List;
import java.util.Optional;

import com.EnigmaCamp.SafePaws.utils.enums.AnimalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.EnigmaCamp.SafePaws.entity.Adoption;
import com.EnigmaCamp.SafePaws.utils.enums.AdoptionStatus;

@Repository
public interface AdoptionRepository extends JpaRepository<Adoption, String>, JpaSpecificationExecutor<Adoption> {
    
    Optional<Adoption> findFirstByOrderByInspectionDateDesc();

    List<Adoption> findByAnimalIdAndAdoptionStatus(String id, AdoptionStatus status);
}
