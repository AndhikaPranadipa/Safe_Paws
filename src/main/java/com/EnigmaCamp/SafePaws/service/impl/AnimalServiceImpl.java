package com.EnigmaCamp.SafePaws.service.impl;

import com.EnigmaCamp.SafePaws.service.AuthService;
import com.EnigmaCamp.SafePaws.utils.dto.GenericIdRequest;
import com.EnigmaCamp.SafePaws.utils.dto.animal.AnimalResponse;
import com.EnigmaCamp.SafePaws.utils.dto.animal.AnimalUpdateRequest;
import com.EnigmaCamp.SafePaws.utils.dto.animal.GetAnimalRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
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
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    private final ShelterRepository shelterRepository;

    private AuthService authService;

    @Override
    public AnimalResponse create(AnimalRequest request) {
        UserDetails result = authService.getCurrentUser();

        Shelter shelter =  shelterRepository.findByEmail(result.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));

        Animal animal = animalRepository.saveAndFlush(request.convert(shelter));

        return AnimalResponse.response(animal, shelter);
    }
    @Override
    public Page<AnimalResponse> getAllByUser(Pageable pageable, AnimalRequest request) {

        request.setAnimalStatus(AnimalStatus.AVAILABLE);

        Specification<Animal> specification = GeneralSpecification.getSpecification(request);
        Page<Animal> animals = animalRepository.findAll(specification, pageable);

        List<AnimalResponse> responseList = animals.stream()
                .map(animal -> {
                    Shelter shelter = shelterRepository.findById(animal.getShelter().getId())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shelter Not Found"));
                    return AnimalResponse.response(animal, shelter);
                })
                .toList();

        return new PageImpl<>(responseList, pageable, animals.getTotalElements());
    }

    @Override
    public Page<AnimalResponse> getAllByShelter(Pageable pageable, GetAnimalRequest request) {

        UserDetails result = authService.getCurrentUser();

        Shelter shelter =  shelterRepository.findByEmail(result.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));

        request.setShelter(shelter);

        Specification<Animal> specification = GeneralSpecification.getSpecification(request);
        Page<Animal> animals = animalRepository.findAll(specification, pageable);

        List<AnimalResponse> responseList = animals.stream()
                .map(animal -> AnimalResponse.response(animal,shelter))
                .collect(Collectors.toList());

        return new PageImpl<>(responseList, pageable, animals.getTotalElements());
    }

    @Override
    public AnimalResponse getById(GenericIdRequest request) {
        Animal animal = animalRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Animal not found"));

        Shelter shelter = shelterRepository.findById(animal.getShelter().getId())
                .orElseThrow(() -> new RuntimeException("Shelter not found"));

        return AnimalResponse.response(animal,shelter);
    }

    @Override
    public AnimalResponse update(AnimalUpdateRequest request) {
        Animal animal = animalRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Animal not found"));

        Shelter shelter = shelterRepository.findById(animal.getShelter().getId())
                .orElseThrow(() -> new RuntimeException("Shelter not found"));

        animal.setName(request.getName());
        animal.setSpecies(request.getSpecies());
        animal.setBreed(request.getBreed());
        animal.setAge(request.getAge());
        animal.setWeight(request.getWeight());
        animal.setAnimalStatus(request.getAnimalStatus());
        animal.setDescription(request.getDescription());

        animalRepository.saveAndFlush(animal);

        return AnimalResponse.response(animal, shelter);
    }

    @Override
    public void delete(GenericIdRequest request) {
     animalRepository.deleteById(request.getId());
    }

}

