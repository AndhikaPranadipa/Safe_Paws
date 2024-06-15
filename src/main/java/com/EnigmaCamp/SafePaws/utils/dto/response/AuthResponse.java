package com.EnigmaCamp.SafePaws.utils.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {

    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }
}
