package com.EnigmaCamp.SafePaws.service.impl;

import com.EnigmaCamp.SafePaws.entity.AddressCustomer;
import com.EnigmaCamp.SafePaws.entity.City;
import com.EnigmaCamp.SafePaws.entity.Customer;
import com.EnigmaCamp.SafePaws.repository.AddressCustomerRepository;
import com.EnigmaCamp.SafePaws.repository.CityRepository;
import com.EnigmaCamp.SafePaws.repository.CustomerRepository;
import com.EnigmaCamp.SafePaws.service.AddressCustomerService;
import com.EnigmaCamp.SafePaws.service.AuthService;
import com.EnigmaCamp.SafePaws.utils.dto.request.AddressCustomerDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class AddressCustomerServiceImpl implements AddressCustomerService {

    private final AddressCustomerRepository addressCustomerRepository;

    private final AuthService authService;

    private final CustomerRepository customerRepository;

    private final CityRepository cityRepository;

    @Override
    public AddressCustomer create(AddressCustomerDTO request) {

        UserDetails result = authService.getCurrentUser();

        Customer customer = customerRepository.findByEmail(result.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
        City city = cityRepository.findById(request.getCityId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));

        return addressCustomerRepository.saveAndFlush(request.toEntity(customer, city));
    }
}
