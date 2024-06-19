package com.EnigmaCamp.SafePaws.service.impl;

import com.EnigmaCamp.SafePaws.entity.Shelter;
import com.EnigmaCamp.SafePaws.repository.ShelterRepository;
import com.EnigmaCamp.SafePaws.service.AuthService;
import com.EnigmaCamp.SafePaws.service.ShelterService;
import com.EnigmaCamp.SafePaws.utils.dto.shelter.request.ShelterDTO;
import com.EnigmaCamp.SafePaws.utils.dto.shelter.request.UpdateShelterDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class ShelterServiceImpl implements ShelterService {
    private final ShelterRepository shelterRepository;

    private AuthService authService;

    private PasswordEncoder passwordEncoder;

    @Override
    public Shelter getByJWT(){
        UserDetails result = authService.getCurrentUser();

        return shelterRepository.findByEmail(result.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));
    }

    @Override
    public Shelter updateShelter(UpdateShelterDTO request)
    {
        UserDetails result = authService.getCurrentUser();

        Shelter shelter = shelterRepository.findByEmail(result.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));

        request.setPassword(passwordEncoder.encode(request.getPassword()));

        shelter.setName(request.getName());
        shelter.setPhone(request.getPhone());
        shelter.setPassword(request.getPassword());
        shelter.setDescription(request.getDescription());

        return shelterRepository.saveAndFlush(shelter);
    }
}
