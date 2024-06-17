package com.EnigmaCamp.SafePaws.controller;

import com.EnigmaCamp.SafePaws.entity.AddressShelter;
import com.EnigmaCamp.SafePaws.entity.Shelter;
import com.EnigmaCamp.SafePaws.service.AddressShelterService;
import com.EnigmaCamp.SafePaws.service.ShelterService;
import com.EnigmaCamp.SafePaws.utils.dto.AddressResponse;
import com.EnigmaCamp.SafePaws.utils.dto.DeleteAddressDTO;
import com.EnigmaCamp.SafePaws.utils.dto.Res;
import com.EnigmaCamp.SafePaws.utils.dto.shelter.request.AddressShelterDTO;
import com.EnigmaCamp.SafePaws.utils.dto.shelter.request.UpdateAddressShelterDTO;
import com.EnigmaCamp.SafePaws.utils.dto.shelter.request.UpdateShelterDTO;
import com.EnigmaCamp.SafePaws.utils.dto.shelter.response.ShelterResponseDTO;
import com.EnigmaCamp.SafePaws.utils.dto.user.request.UpdateAddressUserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping(path = "/address")
    public ResponseEntity<?> setAddress(){

        List<AddressResponse> result = addressShelterService.getAll();

        return Res.renderJson(result, "Success get all address data", HttpStatus.OK);
    }

    @PostMapping(path = "/address")
    public ResponseEntity<?> setAddress(@RequestBody AddressShelterDTO request) throws JsonProcessingException {

        AddressResponse result = addressShelterService.create(request);

        return Res.renderJson(result, "Address shelter added successfully", HttpStatus.CREATED);
    }

    @PutMapping(path = "/address")
    public ResponseEntity<?> putAddress(@RequestBody UpdateAddressShelterDTO request) throws JsonProcessingException {

        AddressResponse result = addressShelterService.update(request);

        return Res.renderJson(result, "Address shelter edited successfully", HttpStatus.OK);
    }

    @DeleteMapping(path = "/address")
    public ResponseEntity<?> delete(@RequestBody DeleteAddressDTO request) throws JsonProcessingException {

        addressShelterService.delete(request);

        return Res.renderJson(null, "Address user deleted successfully", HttpStatus.OK);
    }



}
