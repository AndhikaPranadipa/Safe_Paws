package com.EnigmaCamp.SafePaws.utils.dto.animal;

import com.EnigmaCamp.SafePaws.entity.Animal;
import com.EnigmaCamp.SafePaws.entity.Shelter;
import com.EnigmaCamp.SafePaws.utils.dto.shelter.response.ShelterResponseDTO;
import com.EnigmaCamp.SafePaws.utils.enums.AnimalStatus;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimalResponse {

    private String id;

    private String shelter;

    private String name;

    private String species;

    private String breed;

    private Integer weight;

    private Integer age;

    private AnimalStatus status;

    private String description;


    public static AnimalResponse response(Animal animal, Shelter shelter) {
        return AnimalResponse.builder()
                    .id(animal.getId())
                    .shelter(shelter.getName())
                    .name(animal.getName())
                    .species(animal.getSpecies())
                    .breed(animal.getBreed())
                    .weight(animal.getWeight())
                    .age(animal.getAge())
                    .status(animal.getAnimalStatus())
                    .description(animal.getDescription())
                    .build();
    }
}
