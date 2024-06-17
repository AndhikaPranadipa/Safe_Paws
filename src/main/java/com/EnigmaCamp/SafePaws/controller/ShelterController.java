package com.EnigmaCamp.SafePaws.controller;

import com.EnigmaCamp.SafePaws.entity.AddressShelter;
import com.EnigmaCamp.SafePaws.entity.Shelter;
import com.EnigmaCamp.SafePaws.service.AddressShelterService;
import com.EnigmaCamp.SafePaws.service.ShelterService;
import com.EnigmaCamp.SafePaws.utils.dto.Res;
import com.EnigmaCamp.SafePaws.utils.dto.shelter.request.AddressShelterDTO;
import com.EnigmaCamp.SafePaws.utils.dto.shelter.request.UpdateShelterDTO;
import com.EnigmaCamp.SafePaws.utils.dto.shelter.response.ShelterResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
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

    private final ShelterService shelterService;

    @GetMapping
    public ResponseEntity<?> getCurrentUser() {
        Shelter shelter = shelterService.getByJWT();
        return Res.renderJson(ShelterResponseDTO.fromUser(shelter), "OK", HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UpdateShelterDTO request) {
        Shelter shelter = shelterService.updateShelter(request);
        return Res.renderJson(ShelterResponseDTO.fromUser(shelter), "OK", HttpStatus.OK);
    }



    @PostMapping(path = "/address")
    public ResponseEntity<?> setAddress(@RequestBody AddressShelterDTO request) throws JsonProcessingException {

        AddressShelter result = addressShelterService.create(request);

        return Res.renderJson(result, "Address shelter added successfully", HttpStatus.CREATED);
    }

}
