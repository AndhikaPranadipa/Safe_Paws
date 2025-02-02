package com.EnigmaCamp.SafePaws.service;

import com.EnigmaCamp.SafePaws.utils.dto.AddressResponse;
import com.EnigmaCamp.SafePaws.utils.dto.GenericIdRequest;
import com.EnigmaCamp.SafePaws.utils.dto.shelter.request.AddressShelterDTO;
import com.EnigmaCamp.SafePaws.utils.dto.shelter.request.UpdateAddressShelterDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface AddressShelterService {

    AddressResponse create(AddressShelterDTO request) throws JsonProcessingException;
    List<AddressResponse> getAll();

    AddressResponse update(UpdateAddressShelterDTO request);

    void delete(GenericIdRequest request);
}
