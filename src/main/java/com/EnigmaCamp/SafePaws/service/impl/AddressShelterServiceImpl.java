package com.EnigmaCamp.SafePaws.service.impl;

import com.EnigmaCamp.SafePaws.entity.AddressShelter;
import com.EnigmaCamp.SafePaws.entity.City;
import com.EnigmaCamp.SafePaws.entity.Shelter;
import com.EnigmaCamp.SafePaws.repository.AddressShelterRepository;
import com.EnigmaCamp.SafePaws.repository.CityRepository;
import com.EnigmaCamp.SafePaws.repository.ShelterRepository;
import com.EnigmaCamp.SafePaws.service.AddressShelterService;
import com.EnigmaCamp.SafePaws.service.AuthService;
import com.EnigmaCamp.SafePaws.service.RestClientAddressService;
import com.EnigmaCamp.SafePaws.utils.dto.request.AddressShelterDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class AddressShelterServiceImpl implements AddressShelterService {

    private final AddressShelterRepository addressShelterRepository;

    private final AuthService authService;

    private final ShelterRepository shelterRepository;

    private final CityRepository cityRepository;

    private final RestClientAddressService restClientAddressService;

    @Override
    public AddressShelter create(AddressShelterDTO request) throws JsonProcessingException {
        restClientAddressService.fetch();

        UserDetails result = authService.getCurrentUser();

        Shelter shelter = shelterRepository.findByEmail(result.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shelter Not Found"));
        City city = cityRepository.findById(request.getCityId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City Not Found"));

        return addressShelterRepository.saveAndFlush(request.toEntity(shelter, city));
    }
}
