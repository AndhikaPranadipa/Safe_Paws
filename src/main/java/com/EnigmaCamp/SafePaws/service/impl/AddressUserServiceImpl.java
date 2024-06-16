package com.EnigmaCamp.SafePaws.service.impl;

import com.EnigmaCamp.SafePaws.entity.AddressUser;
import com.EnigmaCamp.SafePaws.entity.City;
import com.EnigmaCamp.SafePaws.entity.User;
import com.EnigmaCamp.SafePaws.repository.AddressUserRepository;
import com.EnigmaCamp.SafePaws.repository.CityRepository;
import com.EnigmaCamp.SafePaws.repository.UserRepository;
import com.EnigmaCamp.SafePaws.service.AddressUserService;
import com.EnigmaCamp.SafePaws.service.AuthService;
import com.EnigmaCamp.SafePaws.utils.dto.request.AddressUserDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class AddressUserServiceImpl implements AddressUserService {

    private final AddressUserRepository addressUserRepository;

    private final AuthService authService;

    private final UserRepository userRepository;

    private final CityRepository cityRepository;

    @Override
    public AddressUser create(AddressUserDTO request) {

        UserDetails result = authService.getCurrentUser();

        User user = userRepository.findByEmail(result.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
        City city = cityRepository.findById(request.getCityId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));

        return addressUserRepository.saveAndFlush(request.toEntity(user, city));
    }
}
