package com.EnigmaCamp.SafePaws.utils.dto.user.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeleteAddressUserDTO {
    @NotBlank
    private String id;
}
