package com.EnigmaCamp.SafePaws.service;

import com.EnigmaCamp.SafePaws.entity.User;
import com.EnigmaCamp.SafePaws.entity.Shelter;
import com.EnigmaCamp.SafePaws.utils.dto.user.request.UserDTO;
import com.EnigmaCamp.SafePaws.utils.dto.shelter.request.ShelterDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

    User registerCustomer(UserDTO request);

    Shelter registerShelter(ShelterDTO request);

    String authenticateUser(String username, String password);

    UserDetails getCurrentUser();

}
