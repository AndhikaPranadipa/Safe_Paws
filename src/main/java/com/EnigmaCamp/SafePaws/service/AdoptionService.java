package com.EnigmaCamp.SafePaws.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.EnigmaCamp.SafePaws.entity.Adoption;
import com.EnigmaCamp.SafePaws.utils.enums.AdoptionStatus;

public interface AdoptionService {
    Adoption createAdoption(String userId, String animalId);
    Page<Adoption> getAllAdoption(Pageable pageable, Adoption request);
    Adoption updateAdoption(String adoptionId, AdoptionStatus newStatus);
    void delete(String id);
}
