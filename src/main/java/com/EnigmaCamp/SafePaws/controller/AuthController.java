package com.EnigmaCamp.SafePaws.controller;

import com.EnigmaCamp.SafePaws.entity.User;
import com.EnigmaCamp.SafePaws.entity.Shelter;
import com.EnigmaCamp.SafePaws.service.AuthService;
import com.EnigmaCamp.SafePaws.service.RestClientAddressService;
import com.EnigmaCamp.SafePaws.utils.dto.Res;
import com.EnigmaCamp.SafePaws.utils.dto.request.AuthRequest;
import com.EnigmaCamp.SafePaws.utils.dto.request.UserDTO;
import com.EnigmaCamp.SafePaws.utils.dto.request.ShelterDTO;
import com.EnigmaCamp.SafePaws.utils.dto.response.AuthResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
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

    @PostMapping("/register-user")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody UserDTO customer) {

        User result = authService.registerCustomer(customer);

        return Res.renderJson(result, "User registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/register-shelter")
    public ResponseEntity<?> registerShelter(@Valid @RequestBody ShelterDTO shelter) {

        Shelter result = authService.registerShelter(shelter);

        return Res.renderJson(result, "Shelter registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticatecustomer(@Valid @RequestBody AuthRequest authRequest) {
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
