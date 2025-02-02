package com.EnigmaCamp.SafePaws.utils.dto.security.response;

import com.EnigmaCamp.SafePaws.entity.Shelter;
import com.EnigmaCamp.SafePaws.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterResponse {
    private String email;

    public static RegisterResponse fromUser(User user) {
        return RegisterResponse.builder()
                .email(user.getEmail())
                .build();
    }

    public static RegisterResponse fromShelter(Shelter shelter) {
        return RegisterResponse.builder()
                .email(shelter.getEmail())
                .build();
    }
}
