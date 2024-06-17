package com.EnigmaCamp.SafePaws.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.EnigmaCamp.SafePaws.entity.Animal;
import com.EnigmaCamp.SafePaws.utils.dto.animal.AnimalRequest;

public interface AnimalService {
    Animal create(AnimalRequest request);
    Page<Animal> getAllStatusAvailable(Pageable pageable, AnimalRequest request);
    Animal getById(String id);
    Animal update(AnimalRequest request, String id);
    void delete(String id);
}
