package com.EnigmaCamp.SafePaws.service;

import com.EnigmaCamp.SafePaws.utils.dto.GenericIdRequest;
import com.EnigmaCamp.SafePaws.utils.dto.adoption.AdoptionRequest;
import com.EnigmaCamp.SafePaws.utils.dto.adoption.AdoptionResponse;
import com.EnigmaCamp.SafePaws.utils.dto.adoption.UpdateAdoptionRequest;
import com.EnigmaCamp.SafePaws.utils.dto.adoption.UserAdoptionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.EnigmaCamp.SafePaws.entity.Adoption;
import com.EnigmaCamp.SafePaws.utils.enums.AdoptionStatus;

public interface AdoptionService {
    UserAdoptionResponse createAdoption(AdoptionRequest request);
    Page<AdoptionResponse> getAllByShelter(Pageable pageable, Adoption request);
    Page<UserAdoptionResponse> getAllByUser(Pageable pageable, Adoption request);
    AdoptionResponse updateAdoption(UpdateAdoptionRequest request);
    void delete(GenericIdRequest request);

}
