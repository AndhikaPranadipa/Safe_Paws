package com.EnigmaCamp.SafePaws.service;

import com.EnigmaCamp.SafePaws.entity.AddressUser;
import com.EnigmaCamp.SafePaws.utils.dto.AddressResponse;
import com.EnigmaCamp.SafePaws.utils.dto.user.request.AddressUserDTO;
import com.EnigmaCamp.SafePaws.utils.dto.user.request.DeleteAddressUserDTO;
import com.EnigmaCamp.SafePaws.utils.dto.user.request.UpdateAddressUserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface AddressUserService {
    AddressResponse create(AddressUserDTO request) throws JsonProcessingException ;

    List<AddressResponse> getAll();

    AddressResponse update(UpdateAddressUserDTO request);
    void delete(DeleteAddressUserDTO request);

}
