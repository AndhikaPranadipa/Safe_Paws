package com.EnigmaCamp.SafePaws.service;

import com.EnigmaCamp.SafePaws.entity.AddressUser;
import com.EnigmaCamp.SafePaws.utils.dto.user.request.AddressUserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AddressUserService {

    AddressUser create(AddressUserDTO request) throws JsonProcessingException;
}
