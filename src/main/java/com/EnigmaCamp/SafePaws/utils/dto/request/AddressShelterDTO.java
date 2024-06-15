package com.EnigmaCamp.SafePaws.utils.dto.request;

import com.EnigmaCamp.SafePaws.entity.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressShelterDTO {

    private String cityId;

    private String description;

    public AddressShelter toEntity(Shelter reqShelter, City reqCity) {
        return AddressShelter.builder()
                .shelter(reqShelter)
                .city(reqCity)
                .description(description)
                .build();
    }
}
