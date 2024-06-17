package com.EnigmaCamp.SafePaws.controller;

import com.EnigmaCamp.SafePaws.entity.AddressUser;
import com.EnigmaCamp.SafePaws.entity.Shelter;
import com.EnigmaCamp.SafePaws.entity.User;
import com.EnigmaCamp.SafePaws.service.AddressUserService;
import com.EnigmaCamp.SafePaws.service.UserService;
import com.EnigmaCamp.SafePaws.utils.dto.Res;
import com.EnigmaCamp.SafePaws.utils.dto.shelter.request.UpdateShelterDTO;
import com.EnigmaCamp.SafePaws.utils.dto.shelter.response.ShelterResponseDTO;
import com.EnigmaCamp.SafePaws.utils.dto.user.request.AddressUserDTO;
import com.EnigmaCamp.SafePaws.utils.dto.user.request.UpdateUserDTO;
import com.EnigmaCamp.SafePaws.utils.dto.user.response.UserResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasRole('USER')")
@AllArgsConstructor
public class UserController {

    private final AddressUserService addressUserService;

    private final UserService userService;

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

    @PostMapping(path = "/address")
    public ResponseEntity<?> setAddress(@RequestBody AddressUserDTO request) throws JsonProcessingException {

        AddressUser result = addressUserService.create(request);

        return Res.renderJson(result, "Address user successfully", HttpStatus.CREATED);
    }
}
