package com.EnigmaCamp.SafePaws.utils.dto.animal;

import com.EnigmaCamp.SafePaws.entity.Animal;
import com.EnigmaCamp.SafePaws.entity.Shelter;
import com.EnigmaCamp.SafePaws.utils.enums.AnimalStatus;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalUpdateRequest {
    private String id;

    private String name;

    private String species;

    private String breed;

    private Integer weight;

    private Integer age;

    private AnimalStatus animalStatus;

    private String description;


    public Animal convert(Shelter shelter) {
        return Animal.builder()
                .id(id)
                .name(name)
                .species(species)
                .breed(breed)
                .weight(weight)
                .age(age)
                .animalStatus(animalStatus)
                .description(description)
                .shelter(shelter)
                .build();
    }
}
