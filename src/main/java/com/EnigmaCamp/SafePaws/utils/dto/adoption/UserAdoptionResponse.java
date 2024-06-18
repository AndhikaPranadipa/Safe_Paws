package com.EnigmaCamp.SafePaws.utils.dto.adoption;

import com.EnigmaCamp.SafePaws.entity.Adoption;
import com.EnigmaCamp.SafePaws.utils.dto.animal.AnimalResponse;
import com.EnigmaCamp.SafePaws.utils.dto.user.response.UserResponseDTO;
import com.EnigmaCamp.SafePaws.utils.enums.AdoptionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAdoptionResponse {

    private String id;
    private AdoptionStatus adoptionStatus;
    private AnimalResponse animal;
    private LocalDate inspectionDay;

    public static UserAdoptionResponse response(Adoption adoption, AnimalResponse animalResponse) {
        return UserAdoptionResponse.builder()
                .id(adoption.getId())
                .adoptionStatus(adoption.getAdoptionStatus())
                .animal(animalResponse)
                .inspectionDay(adoption.getInspectionDate())
                .build();
    }

}
