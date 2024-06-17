package com.EnigmaCamp.SafePaws.service;

import com.EnigmaCamp.SafePaws.entity.Shelter;
import com.EnigmaCamp.SafePaws.utils.dto.shelter.request.ShelterDTO;
import com.EnigmaCamp.SafePaws.utils.dto.shelter.request.UpdateShelterDTO;

public interface ShelterService {

//    Shelter existsByEmail(String email);
    Shelter getByJWT();

    public Shelter updateShelter(UpdateShelterDTO request);
}
