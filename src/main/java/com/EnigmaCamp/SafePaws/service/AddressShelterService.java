package com.EnigmaCamp.SafePaws.service;

import com.EnigmaCamp.SafePaws.entity.AddressShelter;
import com.EnigmaCamp.SafePaws.utils.dto.request.AddressShelterDTO;

public interface AddressShelterService {

    AddressShelter create(AddressShelterDTO request);
}
