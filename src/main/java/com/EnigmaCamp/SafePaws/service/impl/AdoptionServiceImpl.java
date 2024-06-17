package com.EnigmaCamp.SafePaws.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.EnigmaCamp.SafePaws.entity.Adoption;
import com.EnigmaCamp.SafePaws.entity.Animal;
import com.EnigmaCamp.SafePaws.entity.User;
import com.EnigmaCamp.SafePaws.repository.AdoptionRepository;
import com.EnigmaCamp.SafePaws.repository.AnimalRepository;
import com.EnigmaCamp.SafePaws.repository.UserRepository;
import com.EnigmaCamp.SafePaws.service.AdoptionService;
import com.EnigmaCamp.SafePaws.utils.GeneralSpecification;
import com.EnigmaCamp.SafePaws.utils.enums.AdoptionStatus;
import com.EnigmaCamp.SafePaws.utils.enums.AnimalStatus;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdoptionServiceImpl implements AdoptionService {

    private final AdoptionRepository adoptionRepository;
    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;

    @Override
    public Adoption createAdoption(String userId, String animalId) {
       Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new RuntimeException("Animal not found"));
       User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Animal not found"));
        
        LocalDate inspectionDays;
        Optional<Adoption> latestAdoption = adoptionRepository.findFirstByOrderByInspectionDaysDesc();
        if (latestAdoption.isPresent()) {
            inspectionDays = latestAdoption.get().getInspection_date().plusDays(1);
        } else {
            inspectionDays = LocalDate.now().plusDays(1);
        }
        
        Adoption adoption = Adoption.builder()
                .animal(animal)
                .user(user)
                .adoptionStatus(AdoptionStatus.PENDING)
                .inspection_date(inspectionDays)
                .build();
        
        Adoption saved = adoptionRepository.saveAndFlush(adoption);
        return adoptionRepository.saveAndFlush(saved);
    }

    @Override
    public Page<Adoption> getAllAdoption(Pageable pageable, Adoption request) {
        Specification<Adoption> specification = GeneralSpecification.getSpecification(request);
        return adoptionRepository.findAll(specification, pageable);
    }

    @Override
    public Adoption updateAdoption(String adoptionId, AdoptionStatus newStatus) {
        Adoption adoption = adoptionRepository.findById(adoptionId)
                .orElseThrow(() -> new RuntimeException("Adoption request not found"));
                
        adoption.setAdoptionStatus(newStatus);

        if (newStatus == AdoptionStatus.APPROVED) {
            List<Adoption> pendingAdoptions = adoptionRepository.findByAnimalIdAndStatus(adoption.getAnimal().getId(), AdoptionStatus.PENDING);

            for(Adoption pendingAdoption : pendingAdoptions) {
                pendingAdoption.setAdoptionStatus(AdoptionStatus.REJECTED);
            }

            Animal animal = adoption.getAnimal();
            animal.setAnimalStatus(AnimalStatus.ADOPTED);
            animalRepository.save(animal);
            
        } else if (newStatus == AdoptionStatus.REJECTED) {
            adoptionRepository.delete(adoption);
            return null;
        }
        return adoptionRepository.save(adoption);
    }

    @Override
    public void delete(String id) {
        adoptionRepository.deleteById(id);
    }
}
