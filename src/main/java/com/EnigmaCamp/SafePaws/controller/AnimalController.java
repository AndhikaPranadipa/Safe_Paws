package com.EnigmaCamp.SafePaws.controller;

import com.EnigmaCamp.SafePaws.entity.Animal;
import com.EnigmaCamp.SafePaws.service.AnimalService;
import com.EnigmaCamp.SafePaws.utils.dto.PageResponse;
import com.EnigmaCamp.SafePaws.utils.dto.Res;
import com.EnigmaCamp.SafePaws.utils.dto.animal.AnimalRequest;
import com.EnigmaCamp.SafePaws.utils.dto.animal.AnimalResponse;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

      private final AnimalService animalService;

    @PostMapping
    public ResponseEntity<?> createAnimal(@RequestBody AnimalRequest request) {
        AnimalResponse response = AnimalResponse.response(animalService.create(request));
        return Res.renderJson(response, "Data Created", HttpStatus.CREATED);
    }

    @GetMapping(path = "/available")
    public ResponseEntity<?> getAnimalAvailable(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable page,
            @ModelAttribute AnimalRequest request
    ) {
        PageResponse<Animal> responses = new PageResponse<>(animalService.getAllStatusAvailable(page, request));
        return Res.renderJson(responses, "Data Found", HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Animal> getById(@RequestParam String id) {
        Animal response = animalService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> update(@PathVariable String id, @RequestBody AnimalRequest request) {
        Animal animal = animalService.update(request, id);
        return ResponseEntity.ok(animal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Animal> delete(@PathVariable String id) {
        animalService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
