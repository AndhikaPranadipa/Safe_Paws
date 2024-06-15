package com.EnigmaCamp.SafePaws.controller;

import com.EnigmaCamp.SafePaws.entity.AddressShelter;
import com.EnigmaCamp.SafePaws.service.AddressShelterService;
import com.EnigmaCamp.SafePaws.utils.dto.Res;
import com.EnigmaCamp.SafePaws.utils.dto.request.AddressShelterDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shelter")
@PreAuthorize("hasRole('SHELTER')")
@AllArgsConstructor
public class ShelterController {

    private final AddressShelterService addressShelterService;

    @GetMapping
    public ResponseEntity<?> hello() {

        String result = "hello";

        return Res.renderJson(result, "Hello anda Shelter", HttpStatus.CREATED);
    }

    @PostMapping(path = "/address")
    public ResponseEntity<?> setAddress(@RequestBody AddressShelterDTO request) {

        AddressShelter result = addressShelterService.create(request);

        return Res.renderJson(result, "Address shelter successfully", HttpStatus.CREATED);
    }
}
