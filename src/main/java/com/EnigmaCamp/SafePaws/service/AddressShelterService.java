package com.EnigmaCamp.SafePaws.service;

import com.EnigmaCamp.SafePaws.entity.AddressShelter;
import com.EnigmaCamp.SafePaws.utils.dto.AddressResponse;
import com.EnigmaCamp.SafePaws.utils.dto.DeleteAddressDTO;
import com.EnigmaCamp.SafePaws.utils.dto.shelter.request.AddressShelterDTO;
import com.EnigmaCamp.SafePaws.utils.dto.shelter.request.UpdateAddressShelterDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface AddressShelterService {

    AddressResponse create(AddressShelterDTO request) throws JsonProcessingException;
    List<AddressResponse> getAll();

    AddressResponse update(UpdateAddressShelterDTO request);

    void delete(DeleteAddressDTO request);
}
