package com.EnigmaCamp.SafePaws.utils.dto.adoption;

import com.EnigmaCamp.SafePaws.entity.Adoption;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdoptionResponse {

    private String animalId;
    private String userId;

    public static AdoptionResponse response(Adoption adoption) {
        return AdoptionResponse.builder()
                    .animalId(adoption.getAnimal().getId())
                    .userId(adoption.getUser().getId())
                    .build();
    }

}
