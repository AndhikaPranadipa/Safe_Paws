package com.EnigmaCamp.SafePaws.service;

import com.EnigmaCamp.SafePaws.entity.AddressCustomer;
import com.EnigmaCamp.SafePaws.utils.dto.request.AddressCustomerDTO;

public interface AddressCustomerService {

    AddressCustomer create(AddressCustomerDTO request);
}
