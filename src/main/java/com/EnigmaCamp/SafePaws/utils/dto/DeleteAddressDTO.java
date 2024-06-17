package com.EnigmaCamp.SafePaws.utils.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeleteAddressDTO {
    @NotBlank
    private String id;
}
