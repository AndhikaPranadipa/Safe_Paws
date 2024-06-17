package com.EnigmaCamp.SafePaws.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.EnigmaCamp.SafePaws.entity.Adoption;
import com.EnigmaCamp.SafePaws.service.AdoptionService;
import com.EnigmaCamp.SafePaws.utils.dto.PageResponse;
import com.EnigmaCamp.SafePaws.utils.dto.Res;
import com.EnigmaCamp.SafePaws.utils.dto.adoption.AdoptionRequest;
import com.EnigmaCamp.SafePaws.utils.enums.AdoptionStatus;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/adoption")
@AllArgsConstructor
public class AdoptionController {

    private final AdoptionService adoptionService;

    @PostMapping
    public ResponseEntity<?> createAdoption(@RequestBody AdoptionRequest request) {
        Adoption response = adoptionService.createAdoption(request.getUserId(), request.getAnimalId());
        return Res.renderJson(response, "Data created", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> index(
        @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable page,
        @ModelAttribute Adoption request
    ) {
        PageResponse<Adoption> responses = new PageResponse<>(adoptionService.getAllAdoption(page, request));
        return Res.renderJson(responses, "Data retrieved", HttpStatus.OK);
    }

    @PutMapping("/{adoptionId}")
    public ResponseEntity<?> updateAdoption(@PathVariable String adoptionId, @RequestParam AdoptionStatus status) {
        Adoption response = adoptionService.updateAdoption(adoptionId, status);
        return Res.renderJson(response, "Data updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        adoptionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
