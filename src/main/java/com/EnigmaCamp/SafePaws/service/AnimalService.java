package com.EnigmaCamp.SafePaws.service;

import com.EnigmaCamp.SafePaws.utils.dto.GenericIdRequest;
import com.EnigmaCamp.SafePaws.utils.dto.animal.AnimalResponse;
import com.EnigmaCamp.SafePaws.utils.dto.animal.AnimalUpdateRequest;
import com.EnigmaCamp.SafePaws.utils.dto.animal.GetAnimalRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.EnigmaCamp.SafePaws.entity.Animal;
import com.EnigmaCamp.SafePaws.utils.dto.animal.AnimalRequest;

public interface AnimalService {
    AnimalResponse create(AnimalRequest request);
    Page<AnimalResponse> getAllByShelter(Pageable pageable, GetAnimalRequest request);
    Page<AnimalResponse> getAllByUser(Pageable pageable, AnimalRequest request);
    AnimalResponse getById(GenericIdRequest request);
    AnimalResponse update(AnimalUpdateRequest request);
    void delete(GenericIdRequest request);
}
