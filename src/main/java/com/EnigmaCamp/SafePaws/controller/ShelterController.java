package com.EnigmaCamp.SafePaws.controller;

import com.EnigmaCamp.SafePaws.entity.Shelter;
import com.EnigmaCamp.SafePaws.service.AddressShelterService;
import com.EnigmaCamp.SafePaws.service.AnimalService;
import com.EnigmaCamp.SafePaws.service.ShelterService;
import com.EnigmaCamp.SafePaws.utils.dto.AddressResponse;
import com.EnigmaCamp.SafePaws.utils.dto.GenericIdRequest;
import com.EnigmaCamp.SafePaws.utils.dto.PageResponse;
import com.EnigmaCamp.SafePaws.utils.dto.Res;
import com.EnigmaCamp.SafePaws.utils.dto.animal.AnimalRequest;
import com.EnigmaCamp.SafePaws.utils.dto.animal.AnimalResponse;
import com.EnigmaCamp.SafePaws.utils.dto.animal.AnimalUpdateRequest;
import com.EnigmaCamp.SafePaws.utils.dto.shelter.request.AddressShelterDTO;
import com.EnigmaCamp.SafePaws.utils.dto.shelter.request.UpdateAddressShelterDTO;
import com.EnigmaCamp.SafePaws.utils.dto.shelter.request.UpdateShelterDTO;
import com.EnigmaCamp.SafePaws.utils.dto.shelter.response.ShelterResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    private final AnimalService animalService;

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
    public ResponseEntity<?> deleteAddress(@RequestBody GenericIdRequest request) throws JsonProcessingException {

        addressShelterService.delete(request);

        return Res.renderJson(null, "Address user deleted successfully", HttpStatus.OK);
    }

    @GetMapping(path = "/animal")
    public ResponseEntity<?> getAnimalByShelter(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable page,
            @ModelAttribute AnimalRequest request
    ) {
        PageResponse<AnimalResponse> responses = new PageResponse<>(animalService.getAllByShelter(page,request));
        return Res.renderJson(responses, "Animal Data Found", HttpStatus.OK);
    }

    @PostMapping(path = "/animal")
    public ResponseEntity<?> createAnimal(@Valid @RequestBody AnimalRequest request) {
        AnimalResponse response = animalService.create(request);
        return Res.renderJson(response, "Animal Data Created", HttpStatus.CREATED);
    }

    @PutMapping(path = "/animal")
    public ResponseEntity<?> updateAnimal(@Valid @RequestBody AnimalUpdateRequest request) {
        AnimalResponse response = animalService.update(request);
        return Res.renderJson(response, "Animal Data Edited", HttpStatus.OK);
    }

    @DeleteMapping(path = "/animal")
    public ResponseEntity<?> deleteAnimal(@RequestBody GenericIdRequest request) throws JsonProcessingException {

        animalService.delete(request);

        return Res.renderJson(null, "Animal Data Deleted", HttpStatus.OK);
    }
}
