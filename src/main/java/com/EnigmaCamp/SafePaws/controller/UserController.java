package com.EnigmaCamp.SafePaws.controller;

import com.EnigmaCamp.SafePaws.entity.AddressUser;
import com.EnigmaCamp.SafePaws.service.AddressUserService;
import com.EnigmaCamp.SafePaws.utils.dto.Res;
import com.EnigmaCamp.SafePaws.utils.dto.request.AddressUserDTO;
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

    @GetMapping
    public ResponseEntity<?> hello() {

        String result = "hello";

        return Res.renderJson(result, "Hello anda User", HttpStatus.CREATED);
    }

    @PostMapping(path = "/address")
    public ResponseEntity<?> setAddress(@RequestBody AddressUserDTO request) throws JsonProcessingException {

        AddressUser result = addressUserService.create(request);

        return Res.renderJson(result, "Address user successfully", HttpStatus.CREATED);
    }
}
