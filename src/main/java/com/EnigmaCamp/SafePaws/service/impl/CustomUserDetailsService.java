package com.EnigmaCamp.SafePaws.service.impl;

import com.EnigmaCamp.SafePaws.entity.Customer;
import com.EnigmaCamp.SafePaws.entity.Shelter;
import com.EnigmaCamp.SafePaws.repository.CustomerRepository;
import com.EnigmaCamp.SafePaws.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ShelterRepository shelterRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Customer> customerUserOptional = customerRepository.findByEmail(email);
        if (customerUserOptional.isPresent()) {
            return customerUserOptional.get();
        }

        Optional<Shelter> shelterUserOptional = shelterRepository.findByEmail(email);
        if (shelterUserOptional.isPresent()) {
            return shelterUserOptional.get();
        }

        throw new UsernameNotFoundException("User with email " + email + " not found");
    }
}