package com.EnigmaCamp.SafePaws.utils.dto.animal;

import com.EnigmaCamp.SafePaws.entity.Animal;
import com.EnigmaCamp.SafePaws.entity.Shelter;
import com.EnigmaCamp.SafePaws.utils.enums.AnimalStatus;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalRequest {

    private String name;

    private String type;

    private String race;

    private Integer weight;

    private Integer age;

    private AnimalStatus animalStatus;

    private String description;

    private String shelter;

    public Animal convert(Shelter shelter) {
        return Animal.builder()
                    .name(name)
                    .type(type)
                    .race(race)
                    .weight(weight)
                    .age(age)
                    .animalStatus(animalStatus)
                    .description(description)
                    .shelter(shelter)
                    .build();
    }
}
