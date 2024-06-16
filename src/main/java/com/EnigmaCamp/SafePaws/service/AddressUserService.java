package com.EnigmaCamp.SafePaws.service;

import com.EnigmaCamp.SafePaws.entity.AddressUser;
import com.EnigmaCamp.SafePaws.utils.dto.request.AddressUserDTO;

public interface AddressUserService {

    AddressUser create(AddressUserDTO request);
}
