package com.EnigmaCamp.SafePaws.utils.dto.adoption;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdoptionRequest {
    
    private String animalId;
    private String userId;
}
