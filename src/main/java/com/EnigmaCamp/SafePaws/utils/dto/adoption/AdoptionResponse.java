package com.EnigmaCamp.SafePaws.utils.dto.adoption;

import com.EnigmaCamp.SafePaws.entity.Adoption;

import com.EnigmaCamp.SafePaws.utils.dto.animal.AnimalRequest;
import com.EnigmaCamp.SafePaws.utils.dto.animal.AnimalResponse;
import com.EnigmaCamp.SafePaws.utils.dto.user.response.UserResponseDTO;
import com.EnigmaCamp.SafePaws.utils.enums.AdoptionStatus;
import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdoptionResponse {

    private String id;
    private AdoptionStatus adoptionStatus;
    private AnimalResponse animal;
    private UserResponseDTO user;
    private LocalDate inspectionDay;

    public static AdoptionResponse response(Adoption adoption, AnimalResponse animalResponse, UserResponseDTO userResponse) {
        return AdoptionResponse.builder()
                .id(adoption.getId())
                .adoptionStatus(adoption.getAdoptionStatus())
                .animal(animalResponse)
                .user(userResponse)
                .inspectionDay(adoption.getInspectionDate())
                .build();
    }

}
