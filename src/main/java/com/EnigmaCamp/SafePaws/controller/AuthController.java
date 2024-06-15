package com.EnigmaCamp.SafePaws.controller;

import com.EnigmaCamp.SafePaws.entity.Customer;
import com.EnigmaCamp.SafePaws.entity.Shelter;
import com.EnigmaCamp.SafePaws.service.AuthService;
import com.EnigmaCamp.SafePaws.service.RestClientAddressService;
import com.EnigmaCamp.SafePaws.utils.dto.Res;
import com.EnigmaCamp.SafePaws.utils.dto.request.AuthRequest;
import com.EnigmaCamp.SafePaws.utils.dto.request.CustomerDTO;
import com.EnigmaCamp.SafePaws.utils.dto.request.ShelterDTO;
import com.EnigmaCamp.SafePaws.utils.dto.response.AuthResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final RestClientAddressService restClientAddressService;

    @PostMapping("/get-city")
    public ResponseEntity<?> registerCustomer() throws JsonProcessingException {
        restClientAddressService.fetch(); // Ambil data city & province & save ke db
        return null;
    }

    @PostMapping("/register-customer")
    public ResponseEntity<?> registerCustomer(@RequestBody CustomerDTO customer) {

        Customer result = authService.registerCustomer(customer);

        return Res.renderJson(result, "Customer registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/register-shelter")
    public ResponseEntity<?> registerShelter(@RequestBody ShelterDTO shelter) {

        Shelter result = authService.registerShelter(shelter);

        return Res.renderJson(result, "Shelter registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticatecustomer(@RequestBody AuthRequest authRequest) {
        String token = authService.authenticateUser(authRequest.getEmail(), authRequest.getPassword());

        AuthResponse result = new AuthResponse(token);

        return Res.renderJson(result, "Login successfully", HttpStatus.ACCEPTED);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        UserDetails result = authService.getCurrentUser();
        return Res.renderJson(result, "OK", HttpStatus.ACCEPTED);
    }
}
