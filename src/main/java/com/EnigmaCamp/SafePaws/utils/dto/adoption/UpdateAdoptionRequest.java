package com.EnigmaCamp.SafePaws.utils.dto.adoption;

import com.EnigmaCamp.SafePaws.utils.enums.AdoptionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdoptionRequest {

    private String adoptionId;

    private AdoptionStatus adoptionStatus;
}
