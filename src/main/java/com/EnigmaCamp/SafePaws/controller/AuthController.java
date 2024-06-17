package com.EnigmaCamp.SafePaws.controller;

import com.EnigmaCamp.SafePaws.entity.User;
import com.EnigmaCamp.SafePaws.entity.Shelter;
import com.EnigmaCamp.SafePaws.service.AuthService;
import com.EnigmaCamp.SafePaws.service.RestClientAddressService;
import com.EnigmaCamp.SafePaws.utils.dto.Res;
import com.EnigmaCamp.SafePaws.utils.dto.security.request.AuthRequest;
import com.EnigmaCamp.SafePaws.utils.dto.user.request.UserDTO;
import com.EnigmaCamp.SafePaws.utils.dto.shelter.request.ShelterDTO;
import com.EnigmaCamp.SafePaws.utils.dto.security.response.AuthResponse;
import com.EnigmaCamp.SafePaws.utils.dto.security.response.RegisterResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final RestClientAddressService restClientAddressService;

    @PostMapping("/register-user")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody UserDTO userDTO) {

        User user = authService.registerCustomer(userDTO);

        return Res.renderJson(RegisterResponse.fromUser(user), "User registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/register-shelter")
    public ResponseEntity<?> registerShelter(@Valid @RequestBody ShelterDTO shelterDTO) {

        Shelter shelter = authService.registerShelter(shelterDTO);

        return Res.renderJson(RegisterResponse.fromShelter(shelter), "Shelter registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticatecustomer(@Valid @RequestBody AuthRequest authRequest) {
        String token = authService.authenticateUser(authRequest.getEmail(), authRequest.getPassword());

        AuthResponse result = new AuthResponse(token);

        return Res.renderJson(result, "Login successfully", HttpStatus.ACCEPTED);
    }

//    @GetMapping("/me")
//    public ResponseEntity<?> getCurrentUser() {
//        UserDetails result = authService.getCurrentUser();
//        return Res.renderJson(result, "OK", HttpStatus.ACCEPTED);
//    }
}
