package com.EnigmaCamp.SafePaws.controller;

import com.EnigmaCamp.SafePaws.utils.dto.Res;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/animal")
@PreAuthorize("hasAnyRole('USER', 'SHELTER')")
@AllArgsConstructor
public class AnimalController {

    @GetMapping
    public ResponseEntity<?> hello() {

        String result = "hello";

        return Res.renderJson(result, "Cek Animal", HttpStatus.CREATED);
    }
}
