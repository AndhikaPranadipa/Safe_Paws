package com.EnigmaCamp.SafePaws.controller;

import com.EnigmaCamp.SafePaws.entity.Animal;
import com.EnigmaCamp.SafePaws.entity.User;
import com.EnigmaCamp.SafePaws.service.AddressUserService;
import com.EnigmaCamp.SafePaws.service.AnimalService;
import com.EnigmaCamp.SafePaws.service.UserService;
import com.EnigmaCamp.SafePaws.utils.dto.AddressResponse;
import com.EnigmaCamp.SafePaws.utils.dto.PageResponse;
import com.EnigmaCamp.SafePaws.utils.dto.Res;
import com.EnigmaCamp.SafePaws.utils.dto.animal.AnimalRequest;
import com.EnigmaCamp.SafePaws.utils.dto.animal.AnimalResponse;
import com.EnigmaCamp.SafePaws.utils.dto.user.request.AddressUserDTO;
import com.EnigmaCamp.SafePaws.utils.dto.GenericIdRequest;
import com.EnigmaCamp.SafePaws.utils.dto.user.request.UpdateAddressUserDTO;
import com.EnigmaCamp.SafePaws.utils.dto.user.request.UpdateUserDTO;
import com.EnigmaCamp.SafePaws.utils.dto.user.response.UserResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
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
@RequestMapping("/api/user")
@PreAuthorize("hasRole('USER')")
@AllArgsConstructor
public class UserController {

    private final AddressUserService addressUserService;

    private final UserService userService;

    private final AnimalService animalService;

    @GetMapping
    public ResponseEntity<?> getCurrentUser() {
        User user = userService.getByJWT();
        return Res.renderJson(UserResponseDTO.fromUser(user), "OK", HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDTO request) {
        User user = userService.updateUser(request);
        return Res.renderJson(UserResponseDTO.fromUser(user), "OK", HttpStatus.OK);
    }

    @GetMapping(path = "/address")
    public ResponseEntity<?> getAddress(){

        List<AddressResponse> result = addressUserService.getAll();

        return Res.renderJson(result, "Success get all address data", HttpStatus.OK);
    }

    @PostMapping(path = "/address")
    public ResponseEntity<?> setAddress(@RequestBody AddressUserDTO request) throws JsonProcessingException {

        AddressResponse result = addressUserService.create(request);

        return Res.renderJson(result, "Address user added successfully", HttpStatus.CREATED);
    }

    @PutMapping(path = "/address")
    public ResponseEntity<?> putAddress(@RequestBody UpdateAddressUserDTO request) throws JsonProcessingException {

        AddressResponse result = addressUserService.update(request);

        return Res.renderJson(result, "Address user edited successfully", HttpStatus.OK);
    }

    @DeleteMapping(path = "/address")
    public ResponseEntity<?> delete(@RequestBody GenericIdRequest request) throws JsonProcessingException {

        addressUserService.delete(request);

        return Res.renderJson(null, "Address user deleted successfully", HttpStatus.OK);
    }

    @GetMapping(path = "/animal")
    public ResponseEntity<?> hello(
            @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable page,
            @ModelAttribute AnimalRequest request
    ) {

        PageResponse<AnimalResponse> responses = new PageResponse<>(animalService.getAllByUser(page, request));
        return Res.renderJson(responses, "Data Found", HttpStatus.OK);
    }
}
