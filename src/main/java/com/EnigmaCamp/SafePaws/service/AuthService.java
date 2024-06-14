package com.EnigmaCamp.SafePaws.service;

import com.EnigmaCamp.SafePaws.entity.Customer;
import com.EnigmaCamp.SafePaws.entity.Shelter;
import com.EnigmaCamp.SafePaws.utils.dto.request.CustomerDTO;
import com.EnigmaCamp.SafePaws.utils.dto.request.ShelterDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

    Customer registerCustomer(CustomerDTO request);

    Shelter registerShelter(ShelterDTO request);

    String authenticateUser(String username, String password);

    UserDetails getCurrentUser();

}
