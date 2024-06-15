package com.EnigmaCamp.SafePaws.utils.dto.response;

import com.EnigmaCamp.SafePaws.entity.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShelterResponseDTO {

    private String name;

    private String email;

    private String phone;

    private String description;

    private City city;

    public static ShelterResponseDTO fromUser(Shelter shelter, AddressShelter addressShelter){
        return ShelterResponseDTO.builder()
                .name(shelter.getName())
                .email(shelter.getEmail())
                .phone(shelter.getPhone())
                .description(shelter.getDescription())
                .city(addressShelter.getCity())
                .build();
    }
}
