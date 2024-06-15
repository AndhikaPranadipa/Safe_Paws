package com.EnigmaCamp.SafePaws.service.impl;

import com.EnigmaCamp.SafePaws.entity.Customer;
import com.EnigmaCamp.SafePaws.entity.Shelter;
import com.EnigmaCamp.SafePaws.repository.CustomerRepository;
import com.EnigmaCamp.SafePaws.repository.ShelterRepository;
import com.EnigmaCamp.SafePaws.security.JwtTokenProvider;
import com.EnigmaCamp.SafePaws.service.AuthService;
import com.EnigmaCamp.SafePaws.utils.dto.request.CustomerDTO;
import com.EnigmaCamp.SafePaws.utils.dto.request.ShelterDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ShelterRepository shelterRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    public Customer registerCustomer(CustomerDTO request) {
        if (customerRepository.existsByEmail(request.getEmail())){
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "sudah ada");
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return customerRepository.saveAndFlush(request.toEntity());
    }

    @Override
    public Shelter registerShelter(ShelterDTO request) {
        if (shelterRepository.existsByEmail(request.getEmail())){
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "sudah ada");
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return shelterRepository.saveAndFlush(request.toEntity());
    }

    @Override
    public String authenticateUser(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        try {
            return jwtTokenProvider.createToken(username, getAuthorisList(authentication));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "login failed", e.getCause());
        }
    }

    public List<String> getAuthorisList(Authentication authentication){
        return  authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        return (UserDetails) authentication.getPrincipal();
    }
}
