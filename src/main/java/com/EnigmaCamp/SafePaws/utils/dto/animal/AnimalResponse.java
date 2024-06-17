package com.EnigmaCamp.SafePaws.utils.dto.animal;

import com.EnigmaCamp.SafePaws.entity.Animal;
import com.EnigmaCamp.SafePaws.utils.dto.shelter.response.ShelterResponseDTO;
import com.EnigmaCamp.SafePaws.utils.enums.AnimalStatus;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimalResponse {

    private String name;

    private String type;

    private String race;

    private Integer weight;

    private Integer age;

    private AnimalStatus status;

    private String description;

    private ShelterResponseDTO shelter;

    public static AnimalResponse response(Animal animal) {
        return AnimalResponse.builder()
                    .name(animal.getName())
                    .type(animal.getType())
                    .race(animal.getRace())
                    .weight(animal.getWeight())
                    .age(animal.getAge())
                    .status(animal.getAnimalStatus())
                    .description(animal.getDescription())
                    .shelter(ShelterResponseDTO.fromUser(animal.getShelter()))
                    .build();
    }
}
