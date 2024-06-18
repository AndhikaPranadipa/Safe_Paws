package com.EnigmaCamp.SafePaws.service;

import com.EnigmaCamp.SafePaws.utils.dto.GenericIdRequest;
import com.EnigmaCamp.SafePaws.utils.dto.animal.AnimalResponse;
import com.EnigmaCamp.SafePaws.utils.dto.animal.AnimalUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.EnigmaCamp.SafePaws.entity.Animal;
import com.EnigmaCamp.SafePaws.utils.dto.animal.AnimalRequest;

public interface AnimalService {
    AnimalResponse create(AnimalRequest request);
    public Page<AnimalResponse> getAllByShelter(Pageable pageable, AnimalRequest request);
    Page<Animal> getAllStatusAvailable(Pageable pageable, AnimalRequest request);
    AnimalResponse getById(GenericIdRequest request);
    AnimalResponse update(AnimalUpdateRequest request);
    void delete(GenericIdRequest request);
}
