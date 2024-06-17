package com.EnigmaCamp.SafePaws.service.impl;

import com.EnigmaCamp.SafePaws.entity.*;
import com.EnigmaCamp.SafePaws.repository.AddressShelterRepository;
import com.EnigmaCamp.SafePaws.repository.CityRepository;
import com.EnigmaCamp.SafePaws.repository.ProvinceRepository;
import com.EnigmaCamp.SafePaws.repository.ShelterRepository;
import com.EnigmaCamp.SafePaws.service.AddressShelterService;
import com.EnigmaCamp.SafePaws.service.AuthService;
import com.EnigmaCamp.SafePaws.service.RestClientAddressService;
import com.EnigmaCamp.SafePaws.utils.dto.AddressResponse;
import com.EnigmaCamp.SafePaws.utils.dto.DeleteAddressDTO;
import com.EnigmaCamp.SafePaws.utils.dto.shelter.request.AddressShelterDTO;
import com.EnigmaCamp.SafePaws.utils.dto.shelter.request.UpdateAddressShelterDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AddressShelterServiceImpl implements AddressShelterService {

    private final AddressShelterRepository addressShelterRepository;

    private final AuthService authService;

    private final ShelterRepository shelterRepository;

    private final CityRepository cityRepository;

    private final ProvinceRepository provinceRepository;

    private final RestClientAddressService restClientAddressService;

    @Override
    public AddressResponse create(AddressShelterDTO request) throws JsonProcessingException {
        restClientAddressService.fetch();

        UserDetails result = authService.getCurrentUser();

        Shelter shelter = shelterRepository.findByEmail(result.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shelter Not Found"));
        City city = cityRepository.findById(request.getCityId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City Not Found"));

        Province province = provinceRepository.findById(city.getProvince().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Province Not Found"));

        AddressShelter addressShelter = addressShelterRepository.saveAndFlush(request.toEntity(shelter,city));

        return AddressResponse.toResponse(addressShelter, city, province);
    }

    @Override
    public List<AddressResponse> getAll(){
        UserDetails result = authService.getCurrentUser();

        Shelter shelter = shelterRepository.findByEmail(result.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shelter Not Found"));

        List<AddressShelter> addressShelters = addressShelterRepository.findByShelterId(shelter.getId());

        List<AddressResponse> responses = new ArrayList<>();

        for (AddressShelter addressShelter : addressShelters) {
            City city = cityRepository.findById(addressShelter.getCity().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City Not Found"));
            Province province = provinceRepository.findById(city.getProvince().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Province Not Found"));
            responses.add(AddressResponse.toResponse(addressShelter, city, province));
        }

        return responses;
    }

    @Override
    public AddressResponse update(UpdateAddressShelterDTO request){
        UserDetails result = authService.getCurrentUser();

        Shelter shelter = shelterRepository.findByEmail(result.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shelter Not Found"));

        AddressShelter addressShelter = addressShelterRepository.findById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address Not Found"));

        City city = cityRepository.findById(request.getCityId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City Not Found"));

        Province province = provinceRepository.findById(city.getProvince().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Province Not Found"));

        addressShelter.setCity(city);
        addressShelter.setDescription(request.getDescription());

        addressShelterRepository.saveAndFlush(addressShelter);

        return AddressResponse.toResponse(addressShelter, city, province);
    }

    @Override
    public void delete(DeleteAddressDTO request){
        UserDetails result = authService.getCurrentUser();

        Shelter shelter = shelterRepository.findByEmail(result.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shelter Not Found"));

        AddressShelter addressShelter = addressShelterRepository.findById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address Not Found"));


        addressShelterRepository.delete(addressShelter);
    }
}
