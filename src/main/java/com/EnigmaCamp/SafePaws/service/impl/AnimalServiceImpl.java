package com.EnigmaCamp.SafePaws.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.EnigmaCamp.SafePaws.entity.Animal;
import com.EnigmaCamp.SafePaws.entity.Shelter;
import com.EnigmaCamp.SafePaws.repository.AnimalRepository;
import com.EnigmaCamp.SafePaws.repository.ShelterRepository;
import com.EnigmaCamp.SafePaws.service.AnimalService;
import com.EnigmaCamp.SafePaws.utils.GeneralSpecification;
import com.EnigmaCamp.SafePaws.utils.dto.animal.AnimalRequest;
import com.EnigmaCamp.SafePaws.utils.enums.AnimalStatus;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    private final ShelterRepository shelterRepository;

    @Override
    public Animal create(AnimalRequest request) {
        Shelter shelter = shelterRepository.findById(request.getShelter())
            .orElseThrow(() -> new RuntimeException("Shelter not found with id " + request.getShelter()));
        return animalRepository.saveAndFlush(request.convert(shelter));
    }

   @Override
    public Page<Animal> getAllStatusAvailable(Pageable pageable, AnimalRequest request) {

        request.setAnimalStatus(AnimalStatus.AVAILABLE);

        Specification<Animal> specification = GeneralSpecification.getSpecification(request);
        return animalRepository.findAll(specification, pageable);
    }

    @Override
    public Animal getById(String id) {
        return animalRepository.findById(id).orElseThrow(() -> new RuntimeException("Animal not found"));
    }

    @Override
    public Animal update(AnimalRequest request, String id) {
        Animal animal = getById(id);
        animal.setName(request.getName());
        animal.setType(request.getType());
        animal.setRace(request.getRace());
        animal.setAge(request.getAge());
        animal.setWeight(request.getWeight());
        animal.setAge(request.getAge());
        animal.setDescription(request.getDescription());
        return animalRepository.save(animal);
    }

    @Override
    public void delete(String id) {
     animalRepository.deleteById(id);
    }

}

