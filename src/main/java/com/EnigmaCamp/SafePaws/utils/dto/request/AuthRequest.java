package com.EnigmaCamp.SafePaws.utils.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest {

    @NotBlank
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank
    private String password;
}
